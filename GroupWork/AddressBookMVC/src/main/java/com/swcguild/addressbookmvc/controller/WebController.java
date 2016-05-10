package com.swcguild.addressbookmvc.controller;

import com.swcguild.addressbookmvc.dao.AddressBookImpl;
import com.swcguild.addressbookmvc.dao.AddressBookable;
import com.swcguild.addressbookmvc.dao.SearchTerm;
import com.swcguild.addressbookmvc.dto.Address;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jake
 */
@Controller
public class WebController
{

    AddressBookable yellowPages;

    @Inject
    public WebController(AddressBookable ab)
    {
        yellowPages = ab;
    }

    @RequestMapping(value =
    {
        "/", "/home"
    }, method = RequestMethod.GET)
    public String displayHomePage()
    {
        return "home";
    }
    
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address getAddress(@PathVariable("id") int id) {
        
        return yellowPages.getAddressById(id);
    }


//    @RequestMapping(value = "/address", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public Address addAddress(@ModelAttribute("Address") Address a)
//    {
//       
//        yellowPages.addAddress(a);
//        return a;
//    }

//
//    @RequestMapping(value = "/listofaddresses", method = RequestMethod.GET)
//    public String displayAddress(Model model)
//    {
//        model.addAttribute("addressList", yellowPages.getList());
//        return "listofaddresses";
//    }
//
//    @RequestMapping(value = "/displayDeleteAddress", method = RequestMethod.GET)
//    public String displayDeleteAddress(HttpServletRequest req, Model model)
//    {
//        int selected = Integer.parseInt(req.getParameter("selected"));
//        Address address = yellowPages.getAddressById(selected);
//        System.out.println(selected);
//        model.addAttribute("toRemove", address);
//        return "removeaddress";
//    }
//
//    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
//    public String deleteAddress(@ModelAttribute("deladdress") Address a, HttpServletRequest req, Model model)
//    {
//
//        int removeId = Integer.parseInt(req.getParameter("toRemove"));
//        a = yellowPages.getAddressById(removeId);
//        yellowPages.removeAddress(a);
//        return "redirect:listofaddresses";
//    }
//    
//    @RequestMapping(value = "/displayEditAddress", method = RequestMethod.GET)
//    public String displayEditAddress(HttpServletRequest req, Model model)
//    {
//        int selected = Integer.parseInt(req.getParameter("selectedEdit"));
//        Address address = yellowPages.getAddressById(selected);
//        model.addAttribute("toEdit", address);
//        return "editaddress";
//    }
//    
//     @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
//    public String editAddress(@ModelAttribute("editthis") Address a, HttpServletRequest req, BindingResult result)
//    {
//
//        int editId = Integer.parseInt(req.getParameter("toEdit"));
//        Address b = yellowPages.getAddressById(editId);
//        yellowPages.removeAddress(b);
//        yellowPages.addAddress(a);
//        return "redirect:listofaddresses";
//    }
//    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchAddress()
    {
        return "search";
    }
    
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Address createAddress(@Valid @RequestBody Address address) {

        yellowPages.addAddress(address);
        return address;
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("id") int id) {
        
        yellowPages.removeAddress(id);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAddress(@PathVariable("id") int id, @RequestBody Address address) {
      
        address.setId(id);
        yellowPages.updateAddress(address);
        
    }
    
    @RequestMapping(value = "search/addresses", method = RequestMethod.POST)
    @ResponseBody
    public List<Address> searchAddress(@RequestBody Map<String, String> searchMap)
    {
// model.addAttribute("addressList", yellowPages.getFilteredList(a));
//        
//        List<Address> searchResult =  yellowPages.getFilteredList(Map<SearchTerm, String> criteria);
//        return searchResult;
        
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
// Determine which search terms have values, translate the String // keys into SearchTerm enums, and set the corresponding values // appropriately.
        String currentTerm = searchMap.get("firstName");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.FIRST_NAME, currentTerm);
        }
        currentTerm = searchMap.get("lastName");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.LAST_NAME, currentTerm);
        }
        currentTerm = searchMap.get("street");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STREET, currentTerm);
        }
        currentTerm = searchMap.get("city");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.CITY, currentTerm);
        }
        currentTerm = searchMap.get("state");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STATE, currentTerm);
        }
        currentTerm = searchMap.get("zip");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.ZIP, currentTerm);
        }
        return yellowPages.getFilteredList(criteriaMap);
        
        
    }
    
    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    @ResponseBody
    public List<Address> getAllAddresses() {
        return yellowPages.getList();
    }
    
    @RequestMapping(value = "/error")
    public String customError(HttpServletRequest request,
            HttpServletResponse response,
            Model model)
    {

        Integer statusCode
                = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);

        String exceptionMessage = null;
        exceptionMessage = httpStatus.getReasonPhrase();
        String requestUri
                = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null)
        {
            requestUri = "Unknown";
        }

        String message = MessageFormat.format("{0} returned for {1}: {2}", statusCode, requestUri, exceptionMessage);

        model.addAttribute("errorMessage", message);
        return "customError";
    }
}
