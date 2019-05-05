package com.movie.phase12.module;

import lombok.Data;

import java.util.Comparator;

@Data
public class Actor implements Comparator<Actor> {

    private int actor_id;
    private String actor_name;
    private int actor_age;
    private String actor_gender;
    private String actor_profile_image;

    @Override
    public int compare(Actor o1, Actor o2) {
        return  o1.actor_name.compareTo(o2.actor_name);
    }

    static Comparator<Actor> comparebyage= new Comparator<Actor>(){
        @Override
        public int compare(Actor o1, Actor o2) {
            if(o1.actor_age>o2.actor_age)
                return 1;
            if(o1.actor_age==o2.actor_age)
                return 0;
            return -1; }
    };
}
