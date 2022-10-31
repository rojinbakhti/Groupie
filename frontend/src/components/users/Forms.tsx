import { Typography, Form, notification } from "antd";
import { UsernameInput, PasswordInput, SubmitButton } from "./Widgets";
import { titleStyle, formStyle } from "../../common/formStyles";
import { Link } from "react-router-dom";
import axios from "axios";
import React, { useContext } from "react";
import GlobalContext from "../../context/GlobalContext";
const { Title } = Typography;
var querystring = require("querystring");

const openNotification = () => {
    notification.open({
        message: "Invalid Username/Password",
        duration: 15,
        onClick: () => {
            console.log("Notification Clicked!");
        },
    });
};

const openNotification2 = () => {
    notification.open({
        message: "Passwords do not match",
        duration: 15,
        onClick: () => {
            console.log("Notification Clicked!");
        },
    });
};

const openNotification3 = () => {
    notification.open({
        message: "Username already exists",
        duration: 15,
        onClick: () => {
            console.log("Notification Clicked!");
        },
    });
};

type LoginProps = {
    wrongLogin: () => void;
};
export const Login: React.FC<LoginProps> = ({ wrongLogin }) => {
    const { login } = useContext(GlobalContext);
    const onFinish = (data: any) => {
        axios
            .post(
                process.env.REACT_APP_HOST_API_URL + "/api/auth/login",
                querystring.stringify(data),
                {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                    },
                }
            )
            .then((res) => {
                login!(res.data.username);
            })
            .catch(function (error) {
                console.log(error.response.status); // 401
                wrongLogin();
                if (error.response.status == 401) {
                    openNotification();
                }
            });
    };

    return (
        <div>
            <Title style={titleStyle}>Log In</Title>
            <Form
                name="login"
                style={formStyle}
                labelCol={{ span: 8 }}
                wrapperCol={{ span: 16 }}
                onFinish={onFinish}
            >
                <UsernameInput />
                <PasswordInput
                    label="Password"
                    name="password"
                    message="Please input your password!"
                />
                <SubmitButton>Login</SubmitButton>

                <p>
                    New around here? <Link to="/signup">Sign Up</Link>
                </p>
            </Form>
        </div>
    );
};

export const SignUp = () => {
    const onFinish = (data: any) => {
        if (data.password != data.confirmPassword) {
            openNotification2();
            return;
        }

        return axios
            .post(
                process.env.REACT_APP_HOST_API_URL + "/api/auth/signup",
                querystring.stringify(data),
                {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                    },
                }
            )
            .then((res) => {
                localStorage.setItem("username", res.data.username);
                console.log(res.data);
                window.location.href =
                    process.env.REACT_APP_HOST_API_URL + "/";
            })
            .catch(function (error) {
                console.log(error.response.status); // 401
                if (error.response.status == 401) {
                    openNotification3();
                }
            });
    };
    return (
        <div>
            <Title style={titleStyle}>Sign Up</Title>
            <Form
                name="signUp"
                style={formStyle}
                labelCol={{ span: 8 }}
                wrapperCol={{ span: 16 }}
                onFinish={onFinish}
            >
                <UsernameInput />
                <PasswordInput
                    label="Password"
                    name="password"
                    message="Please input your password!"
                />
                <PasswordInput
                    label="Confirm Password"
                    name="confirmPassword"
                    message="Please confirm your password!"
                />

                <SubmitButton>Create Account</SubmitButton>

                <p>
                    Already have an account? <Link to="/">Cancel</Link>
                </p>
            </Form>
        </div>
    );
};
