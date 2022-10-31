import { Form, Input, Button } from "antd";

export const UsernameInput: React.FC = () => {
    return (
        <Form.Item
            label="Username"
            name="username"
            rules={[
                {
                    required: true,
                    message: "Please input your username!",
                },
            ]}
        >
            <Input />
        </Form.Item>
    );
};

type PasswordInputProps = {
    label: string;
    name: string;
    message: string;
};

export const PasswordInput: React.FC<PasswordInputProps> = ({
    label,
    name,
    message,
}) => {
    return (
        <Form.Item
            label={label}
            name={name}
            rules={[
                {
                    required: true,
                    message,
                },
            ]}
        >
            <Input.Password />
        </Form.Item>
    );
};

type SubmitButtonProps = {
    children: React.ReactNode;
};

export const SubmitButton: React.FC<SubmitButtonProps> = ({ children }) => {
    return (
        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
            <Button type="primary" htmlType="submit">
                {children}
            </Button>
        </Form.Item>
    );
};
