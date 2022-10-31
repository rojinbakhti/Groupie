import React, { useContext, useEffect, useState } from "react";
import { Typography, Table, Button, Slider, notification } from "antd";
import CSS from "csstype";
import { firstButtonStyle, columns, responseMapper } from "../../common/invitees";
import {
    BackendEvent,
    GroupDate,
    TicketmasterEvent,
    Response,
    SubmitResponse,
} from "../../common/types";
import axios from "axios";
import GlobalContext from "../../context/GlobalContext";
import { useHistory } from "react-router";

const { Title } = Typography;

type UnfinalizedViewProps = {
    groupDate: GroupDate;
};

const UnfinalizedView: React.FC<UnfinalizedViewProps> = ({ groupDate }) => {
    const { username } = useContext(GlobalContext);
    const history = useHistory();

    const [submitResponse, setSubmitResponse] = useState<SubmitResponse>({
        action: "send",
        username: username!,
        events: [],
    });

    useEffect(() => {
        const submitResponse_: SubmitResponse = {
            action: "send",
            username: username!,
            events: [],
        };

        groupDate.events?.forEach((event) => {
            submitResponse_.events.push({
                eventId: event.event_id,
                eventResponse: false,
                excitement: 3,
            });
        });

        setSubmitResponse(submitResponse_);
    }, []);

    const onSubmit = (isDraft: boolean) => {
        axios.post(process.env.REACT_APP_HOST_API_URL + "/api/response", {
            ...submitResponse,
            action: isDraft ? "draft" : "send",
        });
        notification.open({
            message: "Your response has been recorded!",
            duration: 2.5,
        });
        history.push("/");
    };

    return (
        <div>
            {groupDate.events?.map((event) => (
                <Event event={event} setSubmitResponse={setSubmitResponse} />
            ))}
            <Button
                type="primary"
                style={firstButtonStyle}
                onClick={() => onSubmit(false)}
            >
                Submit
            </Button>
            <Button type="default" onClick={() => onSubmit(true)}>
                Save Draft
            </Button>
        </div>
    );
};

type EventProps = {
    event: BackendEvent;
    setSubmitResponse: React.Dispatch<React.SetStateAction<SubmitResponse>>;
};

const Event: React.FC<EventProps> = ({ event, setSubmitResponse }) => {
    const info: TicketmasterEvent = JSON.parse(event.info);
    const [responses, setResponses] = useState<Response[]>([]);

    useEffect(() => {
        axios
            .post(
                process.env.REACT_APP_HOST_API_URL +
                    "/api/get_users_response?event_id=" +
                    event.event_id
            )
            .then((res) => setResponses(res.data));
    }, []);

    const updateExcitement = (value: number) => {
        setSubmitResponse((oldResponse: SubmitResponse) => {
            oldResponse.events.forEach((e) => {
                if (e.eventId == event.event_id) e.excitement = value;
            });
            return oldResponse;
        });
    };

    const updateResponse = (value: boolean) => {
        setSubmitResponse((oldResponse: SubmitResponse) => {
            oldResponse.events.forEach((e) => {
                if (e.eventId == event.event_id) e.eventResponse = value;
            });
            return oldResponse;
        });
    };

    return (
        <div style={eventStyle}>
            <Title level={3}>{info.name}</Title>
            <Title level={4}>
                {info.localDate} {info.localTime} @ {info.venueName}
            </Title>
            <Title level={4}>Invitees</Title>
            <Table dataSource={responseMapper(responses)} columns={columns} />
            <Button
                type="primary"
                style={firstButtonStyle}
                onClick={() => updateResponse(true)}
            >
                Yes
            </Button>
            <Button type="default" onClick={() => updateResponse(false)}>
                No
            </Button>
            <br />
            <Title style={marginTopStyle} level={5}>
                How excited are you?
            </Title>
            <Slider
                style={widthStyle}
                min={1}
                max={5}
                defaultValue={3}
                onChange={(value) => updateExcitement(value)}
            />
        </div>
    );
};

const eventStyle: CSS.Properties = {
    marginLeft: "5em",
    padding: "2em",
    border: "1px solid rgba(0,0,0,0.1)",
    borderRadius: "4px",
    marginBottom: "2em",
};

const marginTopStyle: CSS.Properties = {
    marginTop: "1.5em",
};

const widthStyle: CSS.Properties = {
    width: "300px",
};

export default UnfinalizedView;
