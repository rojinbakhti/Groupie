import { Button, Typography } from "antd";
import { CheckboxChangeEvent } from "antd/lib/checkbox";
import React, { Dispatch, SetStateAction, useState } from "react";
import { EventsFilter, EventsList } from ".";
import { TicketmasterEvent } from "../../common/types";
import CSS from "csstype";
import SelectedEventList from "./SelectedEventList";

type EventsSearchProps = {
    setPage: Dispatch<SetStateAction<number>>;
    selectedEvents: TicketmasterEvent[];
    setSelectedEvents: React.Dispatch<
        React.SetStateAction<TicketmasterEvent[]>
    >;
    setGroupDateName: React.Dispatch<React.SetStateAction<string>>;
};

const { Title } = Typography;

const EventsSearch: React.FC<EventsSearchProps> = ({
    setPage,
    selectedEvents,
    setSelectedEvents,
    setGroupDateName,
}) => {
    const [events, setEvents] = useState<TicketmasterEvent[]>([]);

    const button = selectedEvents.length === 0 ? 0 : 1;

    const onSelectChange = (e: CheckboxChangeEvent, index: number) => {
        const checked = e.target.checked;
        if (checked) {
            const newSelection = [...selectedEvents, events[index]];
            setSelectedEvents((oldSelection) => newSelection);
        } else {
            const newSelection = selectedEvents.filter(
                (event) =>
                    event.name !== events[index].name ||
                    event.localDate !== events[index].localDate ||
                    event.localTime !== events[index].localTime ||
                    event.venueAddress !== events[index].venueAddress
            );
            setSelectedEvents((oldSelection) => newSelection);
        }
    };

    return (
        <>
            <Title style={{ padding: 12 }}>Create Group Date</Title>
            <div style={containerStyle}>
                <div>
                    <EventsFilter
                        setEvents={setEvents}
                        setGroupDateName={setGroupDateName}
                    />
                    {selectedEvents.length !== 0 && (
                        <Title level={3} style={{ padding: 12 }}>
                            Selected Events
                        </Title>
                    )}
                    <SelectedEventList selectedEvents={selectedEvents} />
                </div>

                <EventsList
                    events={events}
                    selectedEvents={selectedEvents}
                    onChange={onSelectChange}
                />
            </div>

            {button === 0 && (
                <Button disabled onClick={() => setPage(1)} style={buttonStyle}>
                    {" "}
                    Next{" "}
                </Button>
            )}
            {button === 1 && (
                <Button onClick={() => setPage(1)} style={buttonStyle}>
                    {" "}
                    Next{" "}
                </Button>
            )}
        </>
    );
};

const containerStyle: CSS.Properties = {
    display: "flex",
};

const buttonStyle: CSS.Properties = {
    marginBottom: "2em",
};

export default EventsSearch;
