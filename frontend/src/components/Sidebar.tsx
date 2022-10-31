import React, { useContext } from "react";
import CSS from "csstype";
import {
    HomeOutlined,
    PlusCircleOutlined,
    SettingOutlined,
    LogoutOutlined,
    BarsOutlined,
} from "@ant-design/icons";
import { Link, useLocation } from "react-router-dom";
import GlobalContext from "../context/GlobalContext";

const Sidebar = () => {
    const { logout } = useContext(GlobalContext);
    const location = useLocation();
    const pathname = location.pathname;

    const showActivityFeeed = () => {
        const activityFeed = document.querySelector(".activity-feed");
        activityFeed?.classList.remove("closed");
    };

    return (
        <div className="sidebar" style={sidebarStyle}>
            <ul style={listStyle}>
                <li className={pathname === "/" ? "selected" : ""}>
                    <Link to="/">
                        <HomeOutlined />
                    </Link>
                </li>
                <li className={pathname === "/propose" ? "selected" : ""}>
                    <Link to="/propose">
                        <PlusCircleOutlined />
                    </Link>
                </li>
                <li className={pathname === "/propose" ? "selected" : ""}>
                    <Link
                        to="#"
                        onClick={() => showActivityFeeed()}
                        className="activity-button"
                    >
                        <BarsOutlined />
                    </Link>
                </li>
                <li className={pathname === "/settings" ? "selected" : ""}>
                    <Link to="/settings">
                        <SettingOutlined />
                    </Link>
                </li>
                <li>
                    <Link to="#" onClick={() => logout!()}>
                        <LogoutOutlined />
                    </Link>
                </li>
            </ul>
        </div>
    );
};

const sidebarStyle: CSS.Properties = {
    position: "fixed",
    height: "100vh",
    top: 0,
    left: 0,
    width: "50px",
    borderRight: "1px solid rgba(0,0,0,0.1)",
    zIndex: 5,
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
};

const listStyle: CSS.Properties = {
    listStyleType: "none",
    margin: 0,
    padding: 0,
};

export default Sidebar;
