import { Button, List } from "antd";
import Title from "antd/lib/typography/Title";
import React from "react";
import { User } from "../common/types";

type SelectedUsersListProps = {
    removeText: string; // Unblock or remove (propose page)
    onRemove: (user: User) => void;
    selectedUsers: User[];
};
const SelectedUsersList: React.FC<SelectedUsersListProps> = ({
    selectedUsers,
    onRemove,
    removeText,
}) => {
    return (
        <>
            {selectedUsers.length !== 0 && (
                <Title level={3} style={{ padding: 12 }}>
                    Invited Users
                </Title>
            )}
            <List
                itemLayout="horizontal"
                dataSource={selectedUsers}
                style={{
                    width: 500,
                    marginBottom: "2em",
                }}
                renderItem={(user, index) => (
                    <List.Item
                        style={{
                            padding: 12,
                        }}
                    >
                        <List.Item.Meta title={user.username} />
                        <Button type="default" onClick={(e) => onRemove(user)}>
                            {removeText}
                        </Button>
                    </List.Item>
                )}
            />
        </>
    );
};

export default SelectedUsersList;
