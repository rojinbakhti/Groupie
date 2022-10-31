import CSS from "csstype";
import { Response } from "./types";

export const columns = [
    {
        title: "Name",
        dataIndex: "name",
        key: "name",
    },
    {
        title: "Response",
        dataIndex: "response",
        key: "response",
    },
    {
        title: "Excitement",
        dataIndex: "excitement",
        key: "excitement",
    },
];

export const firstButtonStyle: CSS.Properties = {
    marginRight: "1em",
};

export const responseMapper = (responses: Response[]) => {
    return responses.map((response, index) => ({
        key: index + 1,
        name: response.user,
        response: response.response,
        excitement: response.excitement,
    }));
};
