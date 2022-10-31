import React from "react";
import ReactTooltip from "react-tooltip";
import { TicketmasterEvent } from "../../common/types";

type EventTooltipProps = {
    event: TicketmasterEvent;
    id: string;
};
const EventTooltip: React.FC<EventTooltipProps> = ({ event, id }) => {
    return (
        <ReactTooltip id={id} place="left" event="click">
            <div>
                <ul>
                    <li>{event.name}</li>
                    <li>
                        {event.localDate} {event.localTime}
                    </li>
                    <li>{event.venueName}</li>
                    <li>
                        <p style={{ maxWidth: "350px" }}>{event.info}</p>
                    </li>
                    <li>{event.url}</li>
                </ul>
            </div>
        </ReactTooltip>
    );
};

export default EventTooltip;
