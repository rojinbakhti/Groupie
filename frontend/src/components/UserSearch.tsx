import { AutoComplete, Typography, Button } from "antd";
import React, { useState } from "react";
import { User } from "../common/types";

type UserSearchProps = {
    title?: string;
    placeholder: string;
    users: User[];
    onSelect?: (user: User) => void;
};

const { Title } = Typography;

const UserSearch: React.FC<UserSearchProps> = ({
    title,
    placeholder,
    users,
    onSelect,
}) => {
    const [query, setQuery] = useState<string>("");
    return (
        <div style={{ padding: 12 }}>
            <Title>{title}</Title>
            <AutoComplete
                options={users
                    .filter((user) => !user.blocked)
                    .filter((user) =>
                        user.username
                            .toLowerCase()
                            .includes(query.toLowerCase())
                    )
                    .map((user) => ({
                        value: user.username,
                        label: user.username,
                        user,
                    }))}
                style={{ width: 200 }}
                placeholder={placeholder}
                value={query}
                onChange={(newQ) => setQuery(newQ)}
                onSelect={(_, option) => {
                    onSelect?.((option as any).user);
                    setQuery("");
                }}
            />
        </div>
    );
};

export default UserSearch;