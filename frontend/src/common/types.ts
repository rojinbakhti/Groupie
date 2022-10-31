export type TicketmasterEvent = {
    name: string,
    url: string,
    localDate: string,
    localTime: string,
    venueName: string,
    venueCity: string ,
    venueState: string ,
    venueCountry: string ,
    venueAddress: string ,
    info: string ,
}

export type Availability = {
    event : string // "Unavailbility" or event name
    startTime : string
    endTime : string
}

export type User = {
    id : string
    username: string
    blocked?: boolean
    availabilities : Availability[]
}

export type CalendarEvent = {
    index : number
    title : string
    start : Date
    end : Date
}

export type UserSettings = {
    name: string
    blocked?: boolean
}

export type BackendEvent = {
    event_id : number
    info : string
}

export type GroupDate = {
    groupdate_id : number
    inviter : string
    status : string
    title : string
    date : string
    events? : BackendEvent[]
}

export type Response = {
    user : string
    response : string
    excitement : number
} 

export type TableResponse = {
    key : number
    name : string
    response : string
    excitement : number
} 

type SubmitResponseEvent = {
    eventId : number
    eventResponse : boolean
    excitement : number
}

export type SubmitResponse = {
    action : "draft" | "send"
    username : string
    events : SubmitResponseEvent[]
} 