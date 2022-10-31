import { GroupDate } from "./types"

export type FilterState = {
    finalized : boolean // true if filtering on this, false if not
    unfinalized : boolean // so if both false, we return any
    responded : boolean
    unresponded : boolean
}

export const filterFinalized = (oldState : FilterState) : FilterState => ({
    ...oldState,
    finalized : true,
    unfinalized : false
})

export const filterUnfinalized = (oldState : FilterState) : FilterState => ({
    ...oldState,
    finalized : false,
    unfinalized : true
})

export const filterResponded = (oldState : FilterState) : FilterState => ({
    ...oldState,
    responded : true,
    unresponded : false
})

export const filterUnresponded = (oldState : FilterState) : FilterState => ({
    ...oldState,
    responded : false,
    unresponded : true
})

export const defaultFilterState = {
    finalized : false,
    unfinalized : false,
    responded : false,
    unresponded : false
}

export const resetFilters = () : FilterState => defaultFilterState

export const filterGroupDates = (groupDates : GroupDate[], filter : FilterState) : GroupDate[] => {
    // We don't want to modify the input array
    let res : GroupDate[] = [...groupDates]
    if (filter.finalized) res = res.filter((groupDate : GroupDate) => groupDate.status.includes("_finalized"))
    if (filter.unfinalized) res = res.filter((groupDate : GroupDate) => groupDate.status.includes("unfinalized"))
    if (filter.responded) res = res.filter((groupDate : GroupDate) => groupDate.status === "invitee_responded")
    if (filter.unresponded) res = res.filter((groupDate : GroupDate) => groupDate.status === "inviter_finalized" || groupDate.status === "inviter_unfinalized")

    return res
}