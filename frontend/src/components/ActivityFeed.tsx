import React, { useContext, useEffect, useRef, useState } from "react";
import CSS from "csstype";
import Filters from "./Filters";
import { defaultFilterState, filterGroupDates } from "../common/filter";
import axios from "axios";
import GlobalContext from "../context/GlobalContext";
import { GroupDate } from "../common/types";
import { useHistory } from "react-router";

const ActivityFeed = () => {
    const { username } = useContext(GlobalContext);
    const [filterState, setFilterState] = useState(defaultFilterState);
    const activityFeed = useRef<HTMLDivElement>(null);
    const [groupDates, setGroupDates] = useState<GroupDate[]>([]);

    useEffect(() => {
        activityFeed.current?.classList.add("closed");

        const listener = (e: Event) => {
            const activityButton = document.querySelector(".activity-button");
            const isClickInside =
                activityFeed.current?.contains(e.target as Node) ||
                activityButton?.contains(e.target as Node);
            const isHidden = activityFeed.current?.classList.contains("closed");

            if (!isClickInside && !isHidden) {
                //the click was outside the activity feed
                activityFeed.current?.classList.add("closed");
            }
        };

        document.addEventListener("click", listener);
    }, [activityFeed]);

    useEffect(() => {
        const fetchGroupDates = async () => {
            let gds: GroupDate[] = [];
            let res = await axios.post(
                process.env.REACT_APP_HOST_API_URL +
                    `/api/get_groupdates?username=${username}&role=inviter`
            );
            gds = res.data;

            res = await axios.post(
                process.env.REACT_APP_HOST_API_URL +
                    `/api/get_groupdates?username=${username}&role=invitee`
            );
            gds = [...gds, ...res.data];

            gds.sort((a, b) => {
                return a.date > b.date ? 1 : -1;
            });

            setGroupDates(gds);
        };
        fetchGroupDates();
    }, []);

    return (
        <div
            className="activity-feed"
            style={activityFeedStyle}
            ref={activityFeed}
        >
            <Filters
                filterState={filterState}
                setFilterState={setFilterState}
            />
            {filterGroupDates(groupDates, filterState).map((gd) => (
                <Activity gd={gd} />
            ))}
        </div>
    );
};

type ActivityProps = {
    gd: GroupDate;
};

const Activity: React.FC<ActivityProps> = ({ gd }) => {
    const { username } = useContext(GlobalContext);
    const history = useHistory();

    let to: string = "";
    if (username == gd.inviter) to = "/inviter?groupdate_id=" + gd.groupdate_id;
    else to = "/invite?groupdate_id=" + gd.groupdate_id;
    return (
        <div
            style={activityStyle}
            onClick={() => {
                history.push(to);
            }}
        >
            <img
                src="https://images.unsplash.com/photo-1637102838603-d1c02da68c66?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2070&q=80"
                style={imageStyle}
            />
            <div>
                <p style={nameStyle}>{gd.title}</p>
                <p>
                    {gd.status.includes("_finalized")
                        ? "Finalized"
                        : "Unfinalized"}
                </p>
            </div>
        </div>
    );
};

const activityFeedStyle: CSS.Properties = {
    height: "100vh",
    width: "400px",
    position: "fixed",
    top: 0,
    left: 0,
    backgroundColor: "rgba(247, 247, 247)",
    padding: "1em",
    zIndex: 6,
    overflow: "scroll",
};

const activityStyle: CSS.Properties = {
    display: "flex",
    alignItems: "center",
    padding: "1em",
    backgroundColor: "white",
    boxShadow: "0 1px 3px hsla(0, 0%, 0.2)",
};

const imageStyle: CSS.Properties = {
    width: "160px",
    height: "100px",
    objectFit: "cover",
    borderRadius: "4px",
    marginRight: "1em",
};

const nameStyle: CSS.Properties = {
    fontWeight: 700,
};

export default ActivityFeed;
