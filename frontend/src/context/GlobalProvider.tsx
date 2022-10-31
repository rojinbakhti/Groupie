import React, { useState } from "react";
import { notification } from "antd";

import GlobalContext, { ContextInterface } from "./GlobalContext";
import { useHistory } from "react-router-dom";

type ProviderProps = {
    children: React.ReactNode;
};

const GlobalProvider: React.FC<ProviderProps> = (props) => {
    const history = useHistory();
    const [context, setContext] = useState<ContextInterface>({
        username: window.localStorage.getItem("username"),
    });

    const login = (username: string) => {
        localStorage.setItem("username", username);
        setContext({
            ...context,
            username,
        });
        window.localStorage.setItem("failedAttempts", JSON.stringify(0));
    };

    const logout = () => {
        if (context.username === null) return;
        localStorage.removeItem("username");
        setContext({
            ...context,
            username: null,
        });

        notification.open({
            message: "You've been logged out",
            duration: 15,
        });

        history.push("/");
    };

    return (
        <GlobalContext.Provider
            value={{
                username: context.username,
                login,
                logout,
            }}
        >
            {props.children}
        </GlobalContext.Provider>
    );
};

export default GlobalProvider;
