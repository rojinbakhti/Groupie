import React from "react";
import { Checkbox, List } from "antd";
import { TicketmasterEvent } from "../../common/types";
import { v4 as uuidv4 } from "uuid";
import { CheckboxChangeEvent } from "antd/lib/checkbox";
import EventTooltip from "./EventTooltip";

export const formatDate = (dateString: string) => {
    const options: Intl.DateTimeFormatOptions = {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "numeric",
        minute: "numeric",
    };
    return new Date(dateString).toLocaleDateString(undefined, options);
};

type EventsListProps = {
    events: TicketmasterEvent[];
    selectedEvents: TicketmasterEvent[];
    onChange: (e: CheckboxChangeEvent, index: number) => void;
};

const EventsList: React.FC<EventsListProps> = ({
    events,
    onChange,
    selectedEvents,
}) => {
    return (
        <List
            itemLayout="horizontal"
            dataSource={events}
            style={{
                marginLeft: "3em",
                width: 500,
            }}
            renderItem={(event, index) => {
                const id = uuidv4();
                return (
                    <List.Item
                        style={{
                            padding: 12,
                        }}
                    >
                        <>
                            <Checkbox
                                style={{
                                    marginRight: 12,
                                }}
                                // onChange={() => onChange?.(index)}
                                onChange={(e) => onChange(e, index)}
                                checked={
                                    selectedEvents.filter(
                                        (e) =>
                                            e.name === event.name &&
                                            e.localDate == event.localDate &&
                                            e.localTime == event.localTime &&
                                            e.venueAddress == event.venueAddress
                                    ).length !== 0
                                }
                            />
                            <List.Item.Meta
                                data-tip="event-info"
                                data-for={id}
                                title={event.name}
                                description={`${
                                    event.venueAddress
                                } | ${formatDate(event.localDate)}`}
                            />
                            <EventTooltip event={event} id={id} />
                        </>
                    </List.Item>
                );
            }}
        />
    );
};

export default EventsList;
