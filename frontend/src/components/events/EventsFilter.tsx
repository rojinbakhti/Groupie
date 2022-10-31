import { Input, DatePicker, Select, Form } from "antd";
import { HomeOutlined } from "@ant-design/icons";
import React, { useCallback } from "react";
import axios from "axios";
import _, { debounce } from "lodash";
import { TicketmasterEvent } from "../../common/types";
import { ISODateString } from "../../common/iso";

const { Search } = Input;
const { RangePicker } = DatePicker;
const { Option } = Select;

const genres = ["Concerts", "Movies", "Sports"];

type EventsFilterProps = {
    setEvents: React.Dispatch<React.SetStateAction<TicketmasterEvent[]>>;
    setGroupDateName: React.Dispatch<React.SetStateAction<string>>;
};
const EventsFilter: React.FC<EventsFilterProps> = ({
    setEvents,
    setGroupDateName,
}) => {
    const onValuesChange = async (changedValues: any, allValues: any) => {
        setGroupDateName(allValues.name ?? "");
        const rightNow = new Date();
        const requestBody = {
            classificationName: allValues.genre ?? "",
            keyword: allValues.keyword ?? "",
            startDateTime: allValues.dateRange
                ? ISODateString(allValues.dateRange[0].toDate())
                : ISODateString(rightNow),
            endDateTime: allValues.dateRange
                ? ISODateString(allValues.dateRange[1].toDate())
                : ISODateString(
                      new Date(rightNow.getTime() + 1000 * 60 * 60 * 24 * 7)
                  ),
            city: allValues.city ?? "",
            stateCode: "",
        };

        const res = await axios.post(
            process.env.REACT_APP_HOST_API_URL + "/api/ticketmaster",
            requestBody
        );
        setEvents(res.data);
    };

    const debouncedChangeHandler = useCallback(
        debounce(onValuesChange, 1000),
        []
    );

    return (
        <Form
            style={{ width: 500, padding: 12 }}
            layout="vertical"
            onValuesChange={debouncedChangeHandler}
        >
            <Form.Item label="Group Date Name" name="name">
                <Input placeholder="Enter the name of the group date" />
            </Form.Item>
            <Form.Item label="Keyword" name="keyword">
                <Input placeholder="Enter your search query" />
            </Form.Item>
            <Form.Item label="Date range" name="dateRange">
                <RangePicker showTime style={{ width: "100%" }} />
            </Form.Item>
            <Form.Item label="Genre" name="genre">
                <Select placeholder="Select a genre">
                    {genres.map((genre) => (
                        <Option value={genre}>{genre}</Option>
                    ))}
                </Select>
            </Form.Item>
            <Form.Item label="City" name="city">
                <Input
                    placeholder="Enter your city"
                    suffix={
                        <HomeOutlined style={{ color: "rgba(0,0,0,.45)" }} />
                    }
                ></Input>
            </Form.Item>
        </Form>
    );
};

export default EventsFilter;
