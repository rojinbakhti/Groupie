import React, { useContext, useState } from "react";
import "react-big-calendar/lib/sass/styles.scss";
import { Calendar, momentLocalizer, SlotInfo } from "react-big-calendar";
import moment from "moment";
import CSS from "csstype";
import { CalendarEvent } from "../common/types";
import { defaultFilterState } from "../common/filter";
import Filters from "../components/Filters";
import { useHistory } from "react-router";
import GlobalContext from "../context/GlobalContext";
import { ISODateString } from "../common/iso";
import axios from "axios";

const localizer = momentLocalizer(moment);

const containerStyle: CSS.Properties = {
    height: "85vh",
    marginTop: "4vh",
    width: "90vw",
    marginLeft: "5vw",
    marginBottom: "12vh",
};

const eventMapper = (events: any[]): CalendarEvent[] => {
    return events.map((event, index) => ({
        index,
        title: event.event,
        start: new Date(event.startTime),
        end: new Date(event.endTime),
    }));
};

const AvailabilityPage: React.FC = () => {
    const { username } = useContext(GlobalContext);
    const [filterState, setFilterState] = useState(defaultFilterState);
    const history = useHistory();
    const [events, setEvents] = useState<any[]>([
        {
            event: "JBConcert",
            startTime: "11-4-2021 15:00:00",
            endTime: "11-4-2021 15:45:00",
        },
    ]);

    const onSelectEvent = (event: CalendarEvent) => {
        const startTime = ISODateString(event.start);
        const endTime = ISODateString(event.end);

        if (event.title === "Unavailable") {
            // Removing unavailability
            const url =
                process.env.REACT_APP_HOST_API_URL +
                "/api/availability" +
                `?removeSlot=true&username=${username}&startTime=${startTime}&endTime=${endTime}&event=unavailable`;

            axios.post(url);
            setEvents((oldEvents) =>
                oldEvents.filter(
                    (e) =>
                        e.event !== event.title ||
                        e.startTime !== startTime ||
                        e.endTime !== endTime
                )
            );
        }
    };

    const onSelectSlot = ({ start, end }: SlotInfo) => {
        //Use start and end to hit backend
        // Update events state var
        const startTime = ISODateString(start as Date);
        const endTime = ISODateString(end as Date);
        const url =
            process.env.REACT_APP_HOST_API_URL +
            "/api/availability" +
            `?addSlot=true&username=${username}&startTime=${startTime}&endTime=${endTime}&event=unavailable`;

        axios.post(url);
        const event = {
            event: "Unavailable",
            startTime,
            endTime,
        };
        setEvents((oldEvents) => [...oldEvents, event]);
    };

    const eventStyleGetter = (event: CalendarEvent) => {
        var style = {
            backgroundColor:
                event.title === "Unavailable"
                    ? "grey"
                    : "rgba(68, 141, 247, 0.9)",
            borderRadius: "2px",
            opacity: 0.9,
            color: "white",
            border: "0px",
            display: "block",
        };
        return {
            style,
        };
    };

    return (
        <div style={containerStyle}>
            <Filters
                filterState={filterState}
                setFilterState={setFilterState}
            />

            <Calendar
                selectable
                defaultDate={new Date()}
                views={["month", "week", "day"]}
                localizer={localizer}
                events={eventMapper(events)}
                onSelectEvent={onSelectEvent}
                onSelectSlot={onSelectSlot}
                eventPropGetter={eventStyleGetter}
            ></Calendar>
        </div>
    );
};

export default AvailabilityPage;
