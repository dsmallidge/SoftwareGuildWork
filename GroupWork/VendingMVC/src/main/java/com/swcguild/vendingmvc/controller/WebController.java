package com.swcguild.vendingmvc.controller;

import com.swcguild.vendingmvc.dao.Inventory;
import com.swcguild.vendingmvc.dto.Change;
import com.swcguild.vendingmvc.dto.Item;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    Inventory snacks;
    Change coins = new Change();
    int balance = 0;
    String message = "";

    @Inject
    public WebController(Inventory goods)
    {
        snacks = goods;
    }

    @RequestMapping(value =
    {
        "/", "/home"
    }, method = RequestMethod.GET)
    public String displayHomePage(Model model)
    {
        model.addAttribute("money", balance);
        model.addAttribute("inventory", snacks.getCollection());
        return "home";
    }

    @RequestMapping(value = "addmoney")
    public String addMoney(HttpServletRequest req, Model model)
    {
        balance = balance + Integer.parseInt(req.getParameter("money"));
        model.addAttribute("money", balance);
        return "redirect:/home";
    }
    
//    @RequestMapping(value = "/balance", method = RequestMethod.PUT)
//    @ResponseBody
//    public int getBalance(HttpServletRequest req, Model model)
//    {
//        balance = balance + Integer.parseInt(req.getParameter("money"));
//        model.addAttribute("money", balance);
//        return balance;
//    }
//    
//    @RequestMapping(value = "/balance", method = RequestMethod.GET)
//    @ResponseBody
//    public int getBalance(Model model)
//    {
//        model.addAttribute("money", balance);
//        return balance;
//    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void purchase(@PathVariable("id") String id, Model model)
    {
        message = "";
        Item i = snacks.getItem(id);
        if ((i.getQuantity() > 0) && (balance >= i.getCost()))
        {
            snacks.vend(id, balance);
            balance -= i.getCost();
            model.addAttribute("money", balance);
            message = ("You have purchased 1 " + id + ".");
        } else
        {
            if (balance < i.getCost())
            {
                message += ("You do not have enough money. ");
            }
            if (i.getQuantity() <= 0)
            {
                message += ("Sold out of" + id);
            }
        }
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Item> getAllItems()
    {
        return snacks.getCollection();
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItem(@PathVariable("id") String id)
    {
        return snacks.getItem(id);
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    @ResponseBody
    public int getBalance()
    {
        return balance;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public String getMessage()
    {
        return message;
    }

    @RequestMapping(value = "/cashOut", method = RequestMethod.GET)
    @ResponseBody
    public Change getChange(Model model)
    {
        coins.calculateChange(balance);
        balance = 0;
        message = ("Thank you.");
        return coins;
    }

}
