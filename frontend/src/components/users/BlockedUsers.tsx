import React from "react";
import { Button } from "antd";
import { UserSettings } from "../../common/types";
import CSS from "csstype";
import axios from "axios";

var querystring = require('querystring');

type BlockedUsersProps = {
    blockedUsers: UserSettings[];
};

const BlockedUsers: React.FC<BlockedUsersProps> = ({ blockedUsers }) => {
    return (
        <div style={blockedUsersStyle}>
            {blockedUsers.map((blockedUser, index) => (
                <BlockedUser
                    blockedUser={blockedUser}
                    isLast={index === blockedUsers.length - 1}
                />
            ))}
        </div>
    );
};

const blockedUsersStyle: CSS.Properties = {
    margin: "1em 0",
    border: "1px solid #D3D3D3",
    borderRadius: "4px",
};

type BlockedUserProps = {
    blockedUser: UserSettings;
    isLast: boolean;
};

function remove(name: any) {
    const username = localStorage.getItem("username");
    const user = name;
    const data = { "username": username, "user": user, "action": "remove" };
    console.log(user);
    return axios.post(process.env.REACT_APP_HOST_API_URL + "/api/setting",
        querystring.stringify(data),
        {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        }).then(res => {
            console.log(res.data);
            window.location.reload();
        }).catch(function (error) {
            console.log(error.response.status);
        });
}

const BlockedUser: React.FC<BlockedUserProps> = ({ blockedUser, isLast }) => (
    <div style={makeStyle(isLast)}>
        <p style={{ marginTop: "revert" }}>@{blockedUser.name}</p>
        <Button type="default" onClick={() => remove(blockedUser.name)}>Unblock</Button>
    </div>
);

const makeStyle = (isLast: boolean): CSS.Properties => {
    const blockedUserStyle: CSS.Properties = {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        borderBottom: isLast ? 0 : "1px solid #D3D3D3",
        padding: "0.5em 1em",
    };
    return blockedUserStyle;
};

export default BlockedUsers;