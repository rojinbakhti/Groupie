import React, { useEffect, useState } from "react";
import { Typography } from "antd";
import CSS from "csstype";
import InviterView from "../components/viewinvite/InviterView";
import { GroupDate } from "../common/types";
import axios from "axios";
import { RouteComponentProps } from "react-router-dom";

const { Title } = Typography;

const Inviter: React.FC<RouteComponentProps> = ({ location }) => {
    const [groupDate, setGroupDate] = useState<GroupDate | null>(null);
    const groupdate_id = new URLSearchParams(location.search).get(
        "groupdate_id"
    );

    useEffect(() => {
        axios
            .post(
                process.env.REACT_APP_HOST_API_URL +
                    "/api/get_events?groupdate_id=" +
                    groupdate_id
            )
            .then((res) => setGroupDate(res.data));
    }, []);

    if (groupDate === null) return null;
    const finalized = groupDate.status.includes("_finalized");

    return (
        <div style={viewInviteStyle}>
            <Title>{groupDate.title}</Title>
            <Title level={3}>
                Status: {finalized ? "Finalized" : "Unfinalized"}
            </Title>
            <InviterView groupDate={groupDate} />
        </div>
    );
};

const viewInviteStyle: CSS.Properties = {
    padding: "3em",
    marginLeft: "2.5em",
};

export default Inviter;
