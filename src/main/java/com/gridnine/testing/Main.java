package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.service.BeforeCurrentTimeFilter;
import com.gridnine.testing.service.TwoHoursPlusOnGroundFilter;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flight = FlightBuilder.createFlights();
        System.out.println("Вывод полного списка рейсов: ");
        flight.forEach(System.out::println);

        System.out.println("======================================================================================");
        System.out.println("Применение фильтра 'который исключает рейсы, \n" +
                "у которых вылет до текущего момента времени': ");
        List<Flight> filter1 = new BeforeCurrentTimeFilter(LocalDateTime.now()).filter(flight);
        filter1.forEach(System.out::println);

        System.out.println("======================================================================================");
        System.out.println("Применение фильтра 'который исключает те рейсы, \n" +
                "у которых имеются сегменты с датой прилёта раньше даты вылета': ");
        List<Flight> filter2 = new ArrivalBeforeDepartureFilter().filter(flight);
        filter2.forEach(System.out::println);

        System.out.println("======================================================================================");
        System.out.println("Применение фильтра 'который исключает только те рейсы, \n" +
                "у которых общее время, проведенное на земле между сегментами, превышает 2 часа': ");
        List<Flight> filter3 = new TwoHoursPlusOnGroundFilter(2).filter(flight);
        filter3.forEach(System.out::println);
    }
}

