import React, { useContext, useEffect, useState } from "react";
import { EventsSearch } from "../components/events";
import UserSearch from "../components/UserSearch";
import CSS from "csstype";
import { TicketmasterEvent, User } from "../common/types";
import axios from "axios";
import GlobalContext from "../context/GlobalContext";
import { Button, notification } from "antd";
import SelectedUsersList from "../components/SelectedUsersList";
import { useHistory } from "react-router";

const EditGroupDate: React.FC = () => {
    const { username } = useContext(GlobalContext);
    const history = useHistory();
    const [page, setPage] = useState(0);
    const [groupDateName, setGroupDateName] = useState("");
    const [selectedEvents, setSelectedEvents] = useState<TicketmasterEvent[]>(
        []
    );

    const [users, setUsers] = useState<User[]>([]);
    const [invitedUsers, setInvitedUsers] = useState<User[]>([]);

    const onSelectUser = async (user: User) => {
        const body = {
            username,
            user: user.username,
            events: selectedEvents,
        };
        const { data: canAdd } = await axios.post(
            process.env.REACT_APP_HOST_API_URL + "/api/addUser",
            body
        );
        if (canAdd) {
            setInvitedUsers((currUsers) => [...invitedUsers, user]);
        } else {
            notification.open({
                message:
                    "This user cannot be added as they are blocked or have a scheduling conflict",
                duration: 2.5,
            });
        }
    };

    const onRemoveUser = (user: User) => {
        setInvitedUsers((currUsers) =>
            currUsers.filter((u) => u.username !== user.username)
        );
    };

    useEffect(() => {
        axios
            .post(process.env.REACT_APP_HOST_API_URL + "/api/getusers")
            .then((res) => setUsers(res.data));
    }, []);

    const onSubmit = (isPropose: boolean) => {
        const requestBody = {
            username,
            title: groupDateName,
            events: selectedEvents,
            users: invitedUsers.map((user) => user.username),
            action: isPropose ? "propose" : "draft",
        };
        axios.post(
            process.env.REACT_APP_HOST_API_URL + "/api/propose",
            requestBody
        );
        notification.open({
            message: "Your group date has been created!",
            duration: 2.5,
        });
        history.push("/");
    };

    return (
        <div style={containerStyle}>
            {page === 0 && (
                <EventsSearch
                    setPage={setPage}
                    selectedEvents={selectedEvents}
                    setSelectedEvents={setSelectedEvents}
                    setGroupDateName={setGroupDateName}
                />
            )}
            {page === 1 && (
                <>
                    <UserSearch
                        title="Add Users"
                        placeholder="Add users"
                        users={users}
                        onSelect={onSelectUser}
                    />
                    <br />
                    <SelectedUsersList
                        removeText="Remove"
                        onRemove={onRemoveUser}
                        selectedUsers={invitedUsers}
                    />
                    <Button onClick={() => onSubmit(true)}> Send</Button>
                    {"\n"}
                    <Button onClick={() => onSubmit(false)}>
                        Save as Draft
                    </Button>
                </>
            )}
        </div>
    );
};

const containerStyle: CSS.Properties = {
    marginLeft: "5em",
};

export default EditGroupDate;
