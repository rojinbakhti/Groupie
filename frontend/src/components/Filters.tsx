import React, { MouseEventHandler } from "react";
import CSS from "csstype";
import {
    defaultFilterState,
    filterFinalized,
    filterResponded,
    FilterState,
    filterUnfinalized,
    filterUnresponded,
} from "../common/filter";

type FilterProps = {
    filterState: FilterState;
    setFilterState: React.Dispatch<React.SetStateAction<FilterState>>;
};

const Filters: React.FC<FilterProps> = ({ filterState, setFilterState }) => {
    return (
        <div style={filterStyle}>
            <Button
                onClick={(_) =>
                    setFilterState((oldState) => filterFinalized(filterState))
                }
            >
                Finalized
            </Button>

            <Button
                onClick={(_) =>
                    setFilterState((oldState) => filterUnfinalized(filterState))
                }
            >
                Unfinalized
            </Button>

            <Button
                onClick={(_) =>
                    setFilterState((oldState) => filterResponded(filterState))
                }
            >
                Responded
            </Button>

            <Button
                onClick={(_) =>
                    setFilterState((oldState) => filterUnresponded(filterState))
                }
            >
                Unresponded
            </Button>
            <Button
                onClick={(_) =>
                    setFilterState((oldState) => defaultFilterState)
                }
            >
                Clear Filters
            </Button>
        </div>
    );
};

type ButtonProps = {
    children: React.ReactNode;
    onClick: MouseEventHandler;
};

const Button: React.FC<ButtonProps> = ({ children, onClick }) => {
    return (
        <button style={buttonStyle} onClick={onClick}>
            {children}
        </button>
    );
};

const filterStyle: CSS.Properties = {
    display: "flex",
    alignItems: "center",
    padding: "1em",
    maxWidth: "100%",
    flexWrap: "wrap",
    gap: "1em",
    borderBottom: "1px solid rgba(0,0,0,0.1)",
    marginBottom: "1em",
};

const buttonStyle: CSS.Properties = {
    borderRadius: "25px",
    padding: "0.75em",
    border: "1px solid rgb(68, 141, 247)",
    color: "rgb(68, 141, 247)",
    backgroundColor: "#fff",
    cursor: "pointer",
};

export default Filters;
