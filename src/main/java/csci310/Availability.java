package csci310;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Availability{

    public ArrayList<Slot> availabilities = new ArrayList<Slot>();

    public void add(String startTime, String endTime, String event){
        Slot slot = new Slot();
        slot.startTime = startTime;
        slot.endTime = endTime;
        slot.event = event;
        availabilities.add(slot);
    }

    public void remove(String startTime, String endTime, String event){
        for (int i =0; i < availabilities.size(); i++){
            if ((availabilities.get(i).startTime).equals(startTime)){
                availabilities.remove(i);
            }
        }
    }

    public String saveAvailability(){
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        String JSONString = gsonObject.toJson(availabilities);
        return JSONString;

    }

    public void readAvailability(String availability){
        availabilities = new Gson().fromJson(
                //ArrayList<Slot> availabilities
                availability, new TypeToken<ArrayList<Slot>>() {}.getType()
        );




    }






}



