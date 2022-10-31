import React, { useContext, useEffect, useState } from "react";
import { Typography, Table, Button, notification } from "antd";
import { columns, responseMapper } from "../../common/invitees";
import { AutoComplete } from "antd";
import {
    BackendEvent,
    GroupDate,
    TicketmasterEvent,
    Response,
} from "../../common/types";
import axios from "axios";
import GlobalContext from "../../context/GlobalContext";
import { useHistory } from "react-router";

const { Title } = Typography;

type InviterViewProps = {
    groupDate: GroupDate;
};
const InviterView: React.FC<InviterViewProps> = ({ groupDate }) => {
    const { username } = useContext(GlobalContext);
    const history = useHistory();
    const [finalizedEvent, setFinalizedEvent] = useState<BackendEvent>(
        groupDate.events![0]
    );
    const finalizedEventInfo = JSON.parse(finalizedEvent.info);
    const [responses, setResponses] = useState<Response[]>([]);

    const [chosenEvent, setChosenEvent] =
        useState<BackendEvent>(finalizedEvent);
    const info: TicketmasterEvent = JSON.parse(chosenEvent.info);
    const [query, setQuery] = useState<string>(info.name);

    useEffect(() => {
        axios
            .post(
                process.env.REACT_APP_HOST_API_URL +
                    "/api/getFinalizedPGD?groupdate_id=" +
                    groupDate.groupdate_id
            )
            .then((res) => {
                const theEvent = groupDate.events!.find(
                    (e: BackendEvent) => e.event_id === res.data.event_id
                );
                if (theEvent) setFinalizedEvent(theEvent);
            });
    }, []);

    useEffect(() => {
        axios
            .post(
                process.env.REACT_APP_HOST_API_URL +
                    "/api/get_users_response?event_id=" +
                    finalizedEvent.event_id
            )
            .then((res) => setResponses(res.data));
    }, [finalizedEvent]);

    const onSubmit = () => {
        axios.post(
            process.env.REACT_APP_HOST_API_URL +
                `/api/finalize?username=${username}&groupdate_id=${groupDate.groupdate_id}&decision=finalize&event_id=${chosenEvent.event_id}`
        );
        notification.open({
            message: "Your response has been recorded!",
            duration: 2.5,
        });
        history.push("/");
    };

    const finalized = groupDate.status.includes("_finalized");

    return (
        <>
            <Title level={4}>Invitees</Title>
            <Table dataSource={responseMapper(responses)} columns={columns} />
            <Title level={4}>
                Algorithm chose: Event {finalizedEventInfo.name}
            </Title>
            <Title level={4}>Choose an event:</Title>
            <AutoComplete
                style={{ width: 300 }}
                options={groupDate.events!.map((event) => ({
                    value: JSON.parse(event.info).name,
                    label: JSON.parse(event.info).name,
                    event,
                }))}
                value={query}
                onChange={(newQ) => setQuery(newQ)}
                onSelect={(_, option) => {
                    setQuery(option.value);
                    setChosenEvent(option.event);
                }}
            />
            <br />
            <Title level={3}>Chosen Event Details</Title>
            <Title level={4}>
                {info.localDate} {info.localTime} @ {info.venueName}
            </Title>
            <br />
            {!finalized && (
                <Button type="default" onClick={onSubmit}>
                    Close Polling and Submit
                </Button>
            )}
        </>
    );
};

export default InviterView;
