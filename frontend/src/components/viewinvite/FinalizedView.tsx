import React, { useContext, useEffect, useState } from "react";
import { Typography, Table, Button, notification } from "antd";
import { firstButtonStyle, columns, responseMapper } from "../../common/invitees";
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

type FinalizedViewProps = {
    groupDate: GroupDate;
};

const FinalizedView: React.FC<FinalizedViewProps> = ({ groupDate }) => {
    const { username } = useContext(GlobalContext);
    const history = useHistory();
    const [finalizedEvent, setFinalizedEvent] = useState<BackendEvent>(
        groupDate.events![0]
    );
    const [responses, setResponses] = useState<Response[]>([]);

    const info: TicketmasterEvent = JSON.parse(finalizedEvent.info);

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

    const onSubmit = (value: boolean) => {
        const decision = value ? "accept" : "decline";
        axios.post(
            process.env.REACT_APP_HOST_API_URL +
                `/api/decision?username=${username}&groupdate_id=${groupDate.groupdate_id}&decision=${decision}`
        );
        notification.open({
            message: "Your response has been recorded!",
            duration: 2.5,
        });
        history.push("/");
    };

    return (
        <>
            <Title level={3}>{info.name}</Title>
            <Title level={4}>
                {info.localDate} {info.localTime} @ {info.venueName}
            </Title>
            <Title level={4}>Invitees</Title>
            <Table dataSource={responseMapper(responses)} columns={columns} />
            <Button
                type="primary"
                style={firstButtonStyle}
                onClick={() => onSubmit(true)}
            >
                Accept
            </Button>
            <Button type="default" onClick={() => onSubmit(false)}>
                Decline
            </Button>
        </>
    );
};

export default FinalizedView;
