import React, { useContext } from "react";
import CSS from "csstype";
import GlobalContext from "../context/GlobalContext";

const Footer = () => {
    const { username } = useContext(GlobalContext);
    const sidebarShowing = username !== null;

    const footerStyle: CSS.Properties = {
        width: sidebarShowing ? "calc(100vw - 50px)" : "100vw",
        padding: "3em 2em",
        display: "flex",
        justifyContent: "space-between",
        backgroundColor: "rgb(68, 141, 247)",
        color: "white",
        position: "absolute",
        bottom: "0",
        left: sidebarShowing ? "50px" : 0, // width of sidebar
    };

    return (
        <footer style={footerStyle}>
            <p>Group Date Application</p>
            <p>A Team 40 Production</p>
        </footer>
    );
};

export default Footer;
