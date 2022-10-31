import React, { useEffect, useState } from "react";
import { Typography } from "antd";
import CSS from "csstype";
import FinalizedView from "../components/viewinvite/FinalizedView";
import UnfinalizedView from "../components/viewinvite/UnfinalizedView";
import { RouteComponentProps } from "react-router";
import axios from "axios";
import { GroupDate } from "../common/types";

const { Title } = Typography;

const ViewInvite: React.FC<RouteComponentProps> = ({ location }) => {
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
            {finalized ? (
                <FinalizedView groupDate={groupDate} />
            ) : (
                <UnfinalizedView groupDate={groupDate} />
            )}
        </div>
    );
};

const viewInviteStyle: CSS.Properties = {
    padding: "3em",
    marginLeft: "2.5em",
};

export default ViewInvite;
