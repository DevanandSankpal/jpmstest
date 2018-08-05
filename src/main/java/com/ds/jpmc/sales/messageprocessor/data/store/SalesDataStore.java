package com.ds.jpmc.sales.messageprocessor.data.store;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ds.jpmc.sales.messageprocessor.exception.EmptyQueueException;
import com.ds.jpmc.sales.messageprocessor.model.Adjustment;
import com.ds.jpmc.sales.messageprocessor.model.Sale;


public class SalesDataStore {


    public static List<Sale> salesData = new ArrayList<Sale>();
    public static List<Adjustment> adjustmentsData = new ArrayList<Adjustment>();
    public static Queue<String> messageQueue = new LinkedList<String>();

    public static String nextEvent() throws EmptyQueueException {
        if (messageQueue.isEmpty()) {
            throw new EmptyQueueException("The queue is empty");
        }
        return messageQueue.poll();
    }

    public static boolean hasNextEvent() {
        return !messageQueue.isEmpty();
    }

    public static int totalSales() {
        return salesData.size();
    }

    public static void addSale(Sale sale) {
    	salesData.add(sale);
    }

    public static void addAdjustment(Adjustment adjustment) {
    	adjustmentsData.add(adjustment);
    }


}
