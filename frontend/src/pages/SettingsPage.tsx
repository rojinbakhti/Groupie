import React, { useEffect, useState } from "react";
import { Typography } from "antd";
import CSS from "csstype";
import BlockedUserSearch from "../components/BlockedUserSearch"; 
import BlockedUsers from "../components/users/BlockedUsers";
import axios from "axios";
import { UserSettings } from "../common/types";

const { Title } = Typography;
let allUsers: UserSettings[]= [];

var querystring = require('querystring');

const SettingsPage: React.FC = () => {
    const [blocked1, setBlocked] = useState<UserSettings[]>();
    let blocked: UserSettings[] = [];
    useEffect(() => {
        const loggedInUsername = localStorage.getItem("username");
        axios.post(process.env.REACT_APP_HOST_API_URL + "/api/getusers").then( res => {
            //console.log(res.data);
            res.data.forEach((element:any)=>{
                if (element.username!=loggedInUsername){
                    allUsers.push({name: element.username});
                }
            });
            console.log("allUsers", allUsers);
        }).catch(function (error) {
            console.log(error.response.status);
            console.log(error.response);
            console.log(error);
        });
      }, []); // run only once

      useEffect(()=>{
        const username = localStorage.getItem("username");
        const data = {"username": username, "user": "", "action": "show"};
        blocked = [];

        axios.post(process.env.REACT_APP_HOST_API_URL + "/api/setting",
            querystring.stringify(data),
            {
                headers: { 
                "Content-Type": "application/x-www-form-urlencoded"
                }
            }).then( res => {
                console.log(res.data);
                console.log("blockedUsersBeforeAdding", blocked)
                var array = res.data.split(',');
                array.forEach((element:any)=>{
                    if (element!= "empty"){
                        blocked.push({name: element});
                    }
                });
                setBlocked(blocked);
                console.log("blockedUsersAfterAdding", blocked)
            }).catch(function (error) {
                console.log(error.response.status) 
            });
      }, []);

    return (
        <>
            <Title style={titleStyle}>Settings</Title>
            <div style={getSettingsContainerStyle()}>
                <div style={getSettingsSectionStyle()}>
                    <Title level={3} style={title2Style}>
                        Block Users
                    </Title>
                    <div style={blockUsersStyle}>
                        <BlockedUserSearch
                            placeholder="Search users"
                            users={allUsers}
                        />
                        <BlockedUsers blockedUsers={blocked1!=undefined? blocked1: blocked } />
                    </div>
                </div>
            </div>
        </>
    );
};

const titleStyle: CSS.Properties = {
    margin: "1em",
    marginLeft: "2em",
};

const title2Style: CSS.Properties = {
    textAlign: "center",
    marginTop: "1em",
};

const settingsContainerStyle: CSS.Properties = {
    display: "flex",
    border: "1px solid darkgrey",
    margin: "0 5em",
    borderRadius: "4px",
    justifyContent:'center', 
    alignItems:'center'
};

const getSettingsContainerStyle = (): CSS.Properties => {
    const mediaQuery = window.matchMedia("(max-width: 1080px)");
    if (mediaQuery.matches) {
        return {
            ...settingsContainerStyle,
            flexDirection: "column",
        };
    }
    return settingsContainerStyle;
};

const settingsSectionStyle: CSS.Properties = {
    width: "50%",
};

const getSettingsSectionStyle = (): CSS.Properties => {
    const mediaQuery = window.matchMedia("(max-width: 1080px)");
    if (mediaQuery.matches) {
        return {
            ...settingsSectionStyle,
            width: "unset",
        };
    }
    return settingsSectionStyle;
};

const blockUsersStyle: CSS.Properties = {
    width: "80%",
    margin: "0 auto",
};

export default SettingsPage;