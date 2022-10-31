import ReactModal from "react-modal";
import { Typography } from "antd";

const { Text } = Typography;

export const LockedOut = () => (
    <ReactModal isOpen={true}>
        <Text type="danger">
            You are currently locked out, please wait a minute before trying to
            log in again
        </Text>
    </ReactModal>
);
