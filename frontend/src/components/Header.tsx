import React from "react";
import CSS from "csstype";
import logo from "../assets/concert3.jpg";

const Header = () => {
    return (
        <header style={headerStyle}>
            <div style={overlayStyle}>
                {" "}
                <h1 style={titleStyle}>Groupie</h1>{" "}
            </div>
            <img src={logo} style={imgStyle} alt="logo" />
        </header>
    );
};

const imgStyle: CSS.Properties = {
    width: "100%",
    height: "100%",
    objectFit: "cover",
};

const overlayStyle: CSS.Properties = {
    height: "fit-content",
    width: "fit-content",
    backgroundColor: "rgb(68, 141, 247)",
    position: "absolute",
    top: "25px",
    left: "35px",
};

const titleStyle: CSS.Properties = {
    color: "white",
    fontSize: "55px",
    fontWeight: "bold",
    margin: "0",
};

const headerStyle: CSS.Properties = {
    margin: "auto",
    height: "20vh",
    width: "100vw",
    display: "flex",
    justifyContent: "space-between",
    backgroundColor: "rgb(68, 141, 247)",
    color: "white",
    zIndex: 6,
};

export default Header;
