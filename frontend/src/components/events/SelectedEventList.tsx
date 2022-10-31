import { List } from "antd";
import React from "react";
import { TicketmasterEvent } from "../../common/types";
import { formatDate } from "./EventsList";

type SelectedEventsListProps = {
    selectedEvents: TicketmasterEvent[];
};

const SelectedEventList: React.FC<SelectedEventsListProps> = ({
    selectedEvents,
}) => {
    return (
        <List
            itemLayout="horizontal"
            dataSource={selectedEvents}
            style={{
                width: 500,
            }}
            renderItem={(
                { name, localDate, localTime, venueAddress, url },
                index
            ) => (
                <List.Item
                    style={{
                        padding: 12,
                    }}
                >
                    <List.Item.Meta
                        title={name}
                        description={`${venueAddress} | ${formatDate(
                            localDate
                        )}`}
                    />
                </List.Item>
            )}
        />
    );
};

export default SelectedEventList;
