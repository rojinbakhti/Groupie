import { AutoComplete, Typography, Button } from "antd";
import React, { useState } from "react";
import { UserSettings } from "../common/types";
import axios from "axios";

type BlockedUserSearchProps = {
    title?: string;
    placeholder: string;
    users: UserSettings[];
};


const { Title } = Typography;
var querystring = require('querystring');

const BlockedUserSearch: React.FC<BlockedUserSearchProps> = ({
    title,
    placeholder,
    users
}) => {
    const [query, setQuery] = useState<string>("");
    const submit = () => {
        if (query){
            console.log("query",query);
            const username = localStorage.getItem("username");
            const user = query;
            const data = {"username": username, "user": user, "action": "add"};
            axios.post(process.env.REACT_APP_HOST_API_URL + "/api/setting",
            querystring.stringify(data),
            {
                headers: { 
                "Content-Type": "application/x-www-form-urlencoded"
                }
            }).then( res => {
                console.log("res.data in submit",res.data);
                window.location.reload();
            }).catch(function (error) {
                console.log(error.response.status) 
                console.log(error.response)
                console.log(error)
            });
        }
      };
    return (
        <div style={{ padding: 12 }}>
            <Title>{title}</Title>
            <AutoComplete
                options={users
                    .filter((user) =>
                        user.name
                            .toLowerCase()
                            .includes(query.toLowerCase())
                    )
                    .map((user) => ({
                        value: user.name,
                        label: user.name,
                        user,
                    }))}
                style={{ width: 200 }}
                placeholder={placeholder}
                value={query}
                onChange={(newQ) => setQuery(newQ)}
            />
           <Button onClick={submit}> Block</Button>
        </div>
    );
};

export default BlockedUserSearch;