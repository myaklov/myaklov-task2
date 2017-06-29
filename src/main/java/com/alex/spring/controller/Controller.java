package com.alex.spring.controller;

import com.alex.spring.Model.TaskList;
import com.alex.spring.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
@org.springframework.stereotype.Controller
public class Controller {

    private final int INTPAGE=10;

    private Service service;
    @Autowired(required = true)
    @Qualifier(value = "Service")
    public void setService(Service service) {
        this.service = service;
    }


   @RequestMapping("/")
    public String home(){
       return "index";
   }

    @RequestMapping(value = "tasks")
    public  ModelAndView listTasks(@RequestParam(value = "status", defaultValue = "all") String status, Model model, Integer page){
        ModelAndView modelAndView = new ModelAndView("tasks");
        TaskList taskList=new TaskList();

        if(status==null) status="";
        List<TaskList> list=this.service.getListTasks(status);

        PagedListHolder<TaskList> pagedListHolder = new PagedListHolder<TaskList>(list);
        pagedListHolder.setPageSize(INTPAGE);
        modelAndView.addObject("status", status);
        modelAndView.addObject("task", taskList);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
        modelAndView.addObject("holder", pagedListHolder);

        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("tasks", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("tasks", pagedListHolder.getPageList());
        }

        return modelAndView;
    }


    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("task") TaskList taskList,@RequestParam("status") String radio) {

        if(radio!=null) {
            if (radio.equals("yes")) {
                taskList.setStatus("done");
            } else {
                taskList.setStatus("not done");
            }
        }


        if(taskList.getId()==0) {
            this.service.addTask(taskList);

        } else this.service.updateTask(taskList);

        return "redirect:/tasks";
    }


    @RequestMapping("/remove/{id}")
    public String removeTask(@PathVariable("id") int id){
        this.service.removeTask(id);
        return "redirect:/tasks";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("task", this.service.getTaskById(id));
        return "tasks";
    }








}
