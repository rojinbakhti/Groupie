import React, { useState, useEffect } from "react";
import "antd/dist/antd.css";
import { Login } from "../components/users/Forms";
import { LockedOut } from "../components/users/Modals";

const LoginPage: React.FC = () => {
    const [showLockedOut, setShowLockedOut] = useState(false);
    useEffect(() => {
        const loginEnabledTimeString: string | null =
            window.localStorage.getItem("loginEnabledTime");
        if (loginEnabledTimeString === null) return;

        const loginEnabledTime: Date = new Date(
            JSON.parse(loginEnabledTimeString)
        );
        const interval = setInterval(() => {
            // Check if current time is greater than loginEnabledTime
            const rightNow = new Date();
            if (rightNow > loginEnabledTime) {
                setShowLockedOut((showLockedOut) => false);
                clearInterval(interval);
            } else {
                setShowLockedOut((showLockedOut) => true);
            }
        }, 1000);

        return () => {
            clearInterval(interval);
        };
    }, []);

    const wrongLogin = () => {
        let failedAttempts: string | null =
            window.localStorage.getItem("failedAttempts");
        let failedAttemptsNum: number;
        if (failedAttempts === null) failedAttemptsNum = 0;
        else failedAttemptsNum = JSON.parse(failedAttempts);

        if (++failedAttemptsNum === 3) {
            setShowLockedOut(true);
            const rightNow = new Date();
            const oneMinLater = new Date(rightNow.getTime() + 60000);
            window.localStorage.setItem(
                "loginEnabledTime",
                JSON.stringify(oneMinLater)
            );
        }

        window.localStorage.setItem(
            "failedAttempts",
            JSON.stringify(failedAttemptsNum % 3)
        );
    };

    return (
        <>
            <Login wrongLogin={wrongLogin} />
            {showLockedOut && <LockedOut />}
        </>
    );
};

export default LoginPage;
