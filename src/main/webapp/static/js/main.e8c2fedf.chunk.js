(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{217:function(e,t,n){},329:function(e,t,n){"use strict";n.r(t);var c=n(0),a=n.n(c),s=n(23),r=n.n(s),i=n(54),o=(n(217),n(36)),l=(n(218),n(337)),d=n(204),u=n(336),j=n(205),b=n(63),h=n(6),m=function(){return Object(h.jsx)(u.a.Item,{label:"Username",name:"username",rules:[{required:!0,message:"Please input your username!"}],children:Object(h.jsx)(j.a,{})})},p=function(e){var t=e.label,n=e.name,c=e.message;return Object(h.jsx)(u.a.Item,{label:t,name:n,rules:[{required:!0,message:c}],children:Object(h.jsx)(j.a.Password,{})})},x=function(e){var t=e.children;return Object(h.jsx)(u.a.Item,{wrapperCol:{offset:8,span:16},children:Object(h.jsx)(b.a,{type:"primary",htmlType:"submit",children:t})})},f={textAlign:"center",marginTop:"2em"},O={width:"500px",margin:"0 auto",padding:"3em",border:"1px solid lightblue",borderRadius:"4px"},g=n(50),v=n.n(g),y=a.a.createContext({username:null}),w=l.a.Title,C=n(119),S=function(e){var t=e.wrongLogin,n=Object(c.useContext)(y).login;return Object(h.jsxs)("div",{children:[Object(h.jsx)(w,{style:f,children:"Log Into Groupie"}),Object(h.jsxs)(u.a,{name:"login",style:O,labelCol:{span:8},wrapperCol:{span:16},onFinish:function(e){v.a.post("https://localhost:8080/api/auth/login",C.stringify(e),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((function(e){n(e.data.username)})).catch((function(e){console.log(e.response.status),t(),401==e.response.status&&d.a.open({message:"Invalid Username/Password",duration:2.5,onClick:function(){console.log("Notification Clicked!")}})}))},children:[Object(h.jsx)(m,{}),Object(h.jsx)(p,{label:"Password",name:"password",message:"Please input your password!"}),Object(h.jsx)(x,{children:"Login"}),Object(h.jsxs)("p",{children:["New around here? ",Object(h.jsx)(i.b,{to:"/signup",children:"Sign Up"})]})]})]})},k=function(){return Object(h.jsxs)("div",{children:[Object(h.jsx)(w,{style:f,children:"Sign Up"}),Object(h.jsxs)(u.a,{name:"signUp",style:O,labelCol:{span:8},wrapperCol:{span:16},onFinish:function(e){if(e.password==e.confirmPassword)return v.a.post("https://localhost:8080/api/auth/signup",C.stringify(e),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((function(e){localStorage.setItem("username",e.data.username),console.log(e.data),window.location.href="https://localhost:8080/invite"})).catch((function(e){console.log(e.response.status),401==e.response.status&&d.a.open({message:"Username already exists",duration:2.5,onClick:function(){console.log("Notification Clicked!")}})}));d.a.open({message:"Passwords do not match",duration:2.5,onClick:function(){console.log("Notification Clicked!")}})},children:[Object(h.jsx)(m,{}),Object(h.jsx)(p,{label:"Password",name:"password",message:"Please input your password!"}),Object(h.jsx)(p,{label:"Confirm Password",name:"confirmPassword",message:"Please confirm your password!"}),Object(h.jsx)(x,{children:"Create Account"}),Object(h.jsxs)("p",{children:["Already have an account? ",Object(h.jsx)(i.b,{to:"/",children:"Cancel"})]})]})]})},T=n(197),I=n.n(T),D=l.a.Text,N=function(){return Object(h.jsx)(I.a,{isOpen:!0,children:Object(h.jsx)(D,{type:"danger",children:"You are currently locked out, please wait a minute before trying to log in again"})})},E=function(){var e=Object(c.useState)(!1),t=Object(o.a)(e,2),n=t[0],a=t[1];Object(c.useEffect)((function(){var e=window.localStorage.getItem("loginEnabledTime");if(null!==e){var t=new Date(JSON.parse(e)),n=setInterval((function(){new Date>t?(a((function(e){return!1})),clearInterval(n)):a((function(e){return!0}))}),1e3);return function(){clearInterval(n)}}}),[]);return Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(S,{wrongLogin:function(){var e,t=window.localStorage.getItem("failedAttempts");if(e=null===t?0:JSON.parse(t),3===++e){a(!0);var n=new Date,c=new Date(n.getTime()+6e4);window.localStorage.setItem("loginEnabledTime",JSON.stringify(c))}window.localStorage.setItem("failedAttempts",JSON.stringify(e%3))}}),n&&Object(h.jsx)(N,{})]})},U=n(35),L=n(339),F=n(101),A=function(e){return new Date(e).toLocaleDateString(void 0,{year:"numeric",month:"long",day:"numeric",hour:"numeric",minute:"numeric"})},P=function(e){var t=e.events,n=e.onChange,c=e.selectedEvents;return Object(h.jsx)(L.b,{itemLayout:"horizontal",dataSource:t,style:{marginLeft:"3em",width:500},renderItem:function(e,t){var a=e.name,s=e.localDate,r=e.localTime,i=e.venueAddress;e.url;return Object(h.jsx)(L.b.Item,{style:{padding:12},children:Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(F.a,{style:{marginRight:12},onChange:function(e){return n(e,t)},checked:0!==c.filter((function(e){return e.name===a&&e.localDate==s&&e.localTime==r&&e.venueAddress==i})).length}),Object(h.jsx)(L.b.Item.Meta,{title:a,description:"".concat(i," | ").concat(A(s))})]})})}})},R=n(146),z=function(e){var t=e.selectedEvents;return Object(h.jsx)(L.b,{itemLayout:"horizontal",dataSource:t,style:{width:500},renderItem:function(e,t){var n=e.name,c=e.localDate,a=(e.localTime,e.venueAddress);e.url;return Object(h.jsx)(L.b.Item,{style:{padding:12},children:Object(h.jsx)(L.b.Item.Meta,{title:n,description:"".concat(a," | ").concat(A(c))})})}})},G=l.a.Title,B={display:"flex"},M={marginBottom:"2em"},J=function(e){var t=e.setPage,n=e.selectedEvents,a=e.setSelectedEvents,s=e.setGroupDateName,r=Object(c.useState)([]),i=Object(o.a)(r,2),l=i[0],d=i[1],u=0===n.length?0:1;return Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(G,{style:{padding:12},children:"Create Group Date"}),Object(h.jsxs)("div",{style:B,children:[Object(h.jsxs)("div",{children:[Object(h.jsx)(ee,{setEvents:d,setGroupDateName:s}),0!==n.length&&Object(h.jsx)(G,{level:3,style:{padding:12},children:"Selected Events"}),Object(h.jsx)(z,{selectedEvents:n})]}),Object(h.jsx)(P,{events:l,selectedEvents:n,onChange:function(e,t){if(e.target.checked){var c=[].concat(Object(R.a)(n),[l[t]]);a((function(e){return c}))}else{var s=n.filter((function(e){return e.name!==l[t].name||e.localDate!==l[t].localDate||e.localTime!==l[t].localTime||e.venueAddress!==l[t].venueAddress}));a((function(e){return s}))}}})]}),0===u&&Object(h.jsxs)(b.a,{disabled:!0,onClick:function(){return t(1)},style:M,children:[" ","Next"," "]}),1===u&&Object(h.jsxs)(b.a,{onClick:function(){return t(1)},style:M,children:[" ","Next"," "]})]})},Y=n(165),q=n.n(Y),H=n(199),W=n(335),V=n(76),K=n(340),Z=n(200);function Q(e){function t(e){return e<10?"0"+e:e}return e.getUTCFullYear()+"-"+t(e.getUTCMonth()+1)+"-"+t(e.getUTCDate())+"T"+t(e.getUTCHours())+":"+t(e.getUTCMinutes())+":"+t(e.getUTCSeconds())+"Z"}j.a.Search;var X=W.a.RangePicker,$=V.a.Option,_=["Concerts","Movies","Sports"],ee=function(e){var t=e.setEvents,n=e.setGroupDateName,a=function(){var e=Object(H.a)(q.a.mark((function e(c,a){var s,r,i,o,l,d,u;return q.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return n(null!==(s=a.name)&&void 0!==s?s:""),l=new Date,d={classificationName:null!==(r=a.genre)&&void 0!==r?r:"",keyword:null!==(i=a.keyword)&&void 0!==i?i:"",startDateTime:a.dateRange?Q(a.dateRange[0]):Q(l),endDateTime:a.dateRange?Q(a.dateRange[1]):Q(new Date(l.getTime()+6048e5)),city:null!==(o=a.city)&&void 0!==o?o:"",stateCode:""},e.next=5,v.a.post("https://localhost:8080/api/ticketmaster",d);case 5:u=e.sent,t(u.data);case 7:case"end":return e.stop()}}),e)})));return function(t,n){return e.apply(this,arguments)}}(),s=Object(c.useCallback)(Object(Z.debounce)(a,1e3),[]);return Object(h.jsxs)(u.a,{style:{width:500,padding:12},layout:"vertical",onValuesChange:s,children:[Object(h.jsx)(u.a.Item,{label:"Group Date Name",name:"name",children:Object(h.jsx)(j.a,{placeholder:"Enter the name of the group date"})}),Object(h.jsx)(u.a.Item,{label:"Keyword",name:"keyword",children:Object(h.jsx)(j.a,{placeholder:"Enter your search query"})}),Object(h.jsx)(u.a.Item,{label:"Date range",name:"dateRange",children:Object(h.jsx)(X,{showTime:!0,style:{width:"100%"}})}),Object(h.jsx)(u.a.Item,{label:"Genre",name:"genre",children:Object(h.jsx)(V.a,{placeholder:"Select a genre",children:_.map((function(e){return Object(h.jsx)($,{value:e,children:e})}))})}),Object(h.jsx)(u.a.Item,{label:"City",name:"city",children:Object(h.jsx)(j.a,{placeholder:"Enter your city",suffix:Object(h.jsx)(K.a,{style:{color:"rgba(0,0,0,.45)"}})})})]})},te=n(333),ne=l.a.Title,ce=n(119),ae=function(e){var t=e.title,n=e.placeholder,a=e.users,s=e.onSelect,r=Object(c.useState)(""),i=Object(o.a)(r,2),l=i[0],d=i[1];return Object(h.jsxs)("div",{style:{padding:12},children:[Object(h.jsx)(ne,{children:t}),Object(h.jsx)(te.a,{options:a.filter((function(e){return!e.blocked})).filter((function(e){return e.username.toLowerCase().includes(l.toLowerCase())})).map((function(e){return{value:e.username,label:e.username,user:e}})),style:{width:200},placeholder:n,value:l,onChange:function(e){return d(e)},onSelect:function(e,t){return null===s||void 0===s?void 0:s(t.user)}}),Object(h.jsx)(b.a,{onClick:function(){if(l){console.log(l);var e={username:localStorage.getItem("username"),user:l,action:"add"};v.a.post("https://localhost:8080/api/setting",ce.stringify(e),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((function(e){console.log(e.data),window.location.reload()})).catch((function(e){console.log(e.response.status),console.log(e.response),console.log(e)}))}},children:" Add"})]})},se={marginLeft:"5em"},re=function(){Object(c.useContext)(y).username;var e=Object(c.useState)(0),t=Object(o.a)(e,2),n=t[0],a=t[1],s=Object(c.useState)(""),r=Object(o.a)(s,2),i=(r[0],r[1]),l=Object(c.useState)([]),d=Object(o.a)(l,2),u=d[0],j=d[1],m=Object(c.useState)([]),p=Object(o.a)(m,2),x=p[0],f=p[1],O=Object(c.useState)([]),g=Object(o.a)(O,2),w=(g[0],g[1],void 0);return Object(c.useEffect)((function(){v.a.post("https://localhost:8080/api/getusers").then((function(e){return f(e.data)}))}),[]),Object(h.jsxs)("div",{style:se,children:[0===n&&Object(h.jsx)(J,{setPage:a,selectedEvents:u,setSelectedEvents:j,setGroupDateName:i}),1===n&&Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(ae,{title:"Add Users",placeholder:"Add users",users:x}),Object(h.jsx)("br",{}),Object(h.jsx)(b.a,{onClick:w,children:" Send"}),"\n",Object(h.jsx)(b.a,{onClick:w,children:" Save as Draft"})]})]})},ie=n(40),oe=n(119),le={margin:"1em 0",border:"1px solid #D3D3D3",borderRadius:"4px"};var de=function(e){var t=e.blockedUser,n=e.isLast;return Object(h.jsxs)("div",{style:ue(n),children:[Object(h.jsxs)("p",{style:{marginTop:"revert"},children:["@",t]}),Object(h.jsx)(b.a,{type:"default",onClick:function(){return function(e){var t=e,n={username:localStorage.getItem("username"),user:t,action:"remove"};return console.log(t),v.a.post("https://localhost:8080/api/setting",oe.stringify(n),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((function(e){console.log(e.data),window.location.reload()})).catch((function(e){console.log(e.response.status)}))}(t)},children:"Unblock"})]})},ue=function(e){return{display:"flex",justifyContent:"space-between",alignItems:"center",borderBottom:e?0:"1px solid #D3D3D3",padding:"0.5em 1em"}},je=function(e){var t=e.blockedUsers;return Object(h.jsx)("div",{style:le,children:t.map((function(e,n){return Object(h.jsx)(de,{blockedUser:e,isLast:n===t.length-1})}))})},be=l.a.Title,he=[],me=[],pe=n(119),xe={margin:"1em",marginLeft:"2em"},fe={textAlign:"center",marginTop:"1em"},Oe={display:"flex",border:"1px solid darkgrey",margin:"0 5em",borderRadius:"4px",justifyContent:"center",alignItems:"center"},ge=function(){return window.matchMedia("(max-width: 1080px)").matches?Object(ie.a)(Object(ie.a)({},Oe),{},{flexDirection:"column"}):Oe},ve={width:"50%"},ye=function(){return window.matchMedia("(max-width: 1080px)").matches?Object(ie.a)(Object(ie.a)({},ve),{},{width:"unset"}):ve},we={width:"80%",margin:"0 auto"},Ce=function(){var e=Object(c.useState)(!0),t=Object(o.a)(e,2),n=(t[0],t[1]),a=Object(c.useState)(),s=Object(o.a)(a,2),r=s[0],i=s[1];return Object(c.useEffect)((function(){var e=localStorage.getItem("username");v.a.post("https://localhost:8080/api/getusers").then((function(t){t.data.forEach((function(t){t.username!=e&&he.push(t)})),console.log("allUsers",he)})).catch((function(e){console.log(e.response.status),console.log(e.response),console.log(e)}))}),[]),Object(c.useEffect)((function(){var e={username:localStorage.getItem("username"),user:"",action:"show"};v.a.post("https://localhost:8080/api/setting",pe.stringify(e),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then((function(e){n(!1),e.data.split(",").forEach((function(e){"empty"!=e&&me.push(e)})),i(me),console.log("blockedUsers",me)})).catch((function(e){console.log(e.response.status)}))}),[r]),Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(be,{style:xe,children:"Settings"}),Object(h.jsx)("div",{style:ge(),children:Object(h.jsxs)("div",{style:ye(),children:[Object(h.jsx)(be,{level:3,style:fe,children:"Block Users"}),Object(h.jsxs)("div",{style:we,children:[Object(h.jsx)(ae,{placeholder:"Search users",users:he}),Object(h.jsx)(je,{blockedUsers:void 0!=r?r:me})]})]})})]})},Se=function(){return Object(h.jsx)(k,{})},ke=(n(328),n(167)),Te=n(71),Ie=n.n(Te),De={finalized:!1,unfinalized:!1,responded:!1,unresponded:!1},Ne=function(e){var t=e.children,n=e.onClick;return Object(h.jsx)("button",{style:Ue,onClick:n,children:t})},Ee={display:"flex",alignItems:"center",padding:"1em",maxWidth:"100%",flexWrap:"wrap",gap:"1em",borderBottom:"1px solid rgba(0,0,0,0.1)",marginBottom:"1em"},Ue={borderRadius:"25px",padding:"0.75em",border:"1px solid rgb(68, 141, 247)",color:"rgb(68, 141, 247)",backgroundColor:"#fff",cursor:"pointer"},Le=function(e){var t=e.filterState,n=e.setFilterState;return Object(h.jsxs)("div",{style:Ee,children:[Object(h.jsx)(Ne,{onClick:function(e){return n((function(e){return function(e){return Object(ie.a)(Object(ie.a)({},e),{},{finalized:!0,unfinalized:!1})}(t)}))},children:"Finalized"}),Object(h.jsx)(Ne,{onClick:function(e){return n((function(e){return function(e){return Object(ie.a)(Object(ie.a)({},e),{},{finalized:!1,unfinalized:!0})}(t)}))},children:"Unfinalized"}),Object(h.jsx)(Ne,{onClick:function(e){return n((function(e){return function(e){return Object(ie.a)(Object(ie.a)({},e),{},{responded:!0,unresponded:!1})}(t)}))},children:"Responded"}),Object(h.jsx)(Ne,{onClick:function(e){return n((function(e){return function(e){return Object(ie.a)(Object(ie.a)({},e),{},{responded:!1,unresponded:!0})}(t)}))},children:"Unresponded"})]})},Fe=Object(ke.b)(Ie.a),Ae={height:"85vh",marginTop:"4vh",width:"90vw",marginLeft:"5vw",marginBottom:"12vh"},Pe=[{event:"JBConcert",startTime:"11-4-2021 15:00:00",endTime:"11-4-2021 15:45:00"}],Re=function(){var e,t=Object(c.useState)(De),n=Object(o.a)(t,2),a=n[0],s=n[1];return Object(h.jsxs)("div",{style:Ae,children:[Object(h.jsx)(Le,{filterState:a,setFilterState:s}),Object(h.jsx)(ke.a,{defaultDate:new Date,views:["month","week","day"],localizer:Fe,events:(e=Pe,e.map((function(e,t){return{index:t,title:e.event,start:new Date(e.startTime),end:new Date(e.endTime)}})))})]})},ze=function(){var e=null!==Object(c.useContext)(y).username,t={width:e?"calc(100vw - 50px)":"100vw",padding:"3em 2em",display:"flex",justifyContent:"space-between",backgroundColor:"rgb(68, 141, 247)",color:"white",position:"absolute",bottom:"0",left:e?"50px":0};return Object(h.jsxs)("footer",{style:t,children:[Object(h.jsx)("p",{children:"Group Date Application"}),Object(h.jsx)("p",{children:"A Team 40 Production"})]})},Ge=n(334),Be=[{title:"Name",dataIndex:"name",key:"name"},{title:"Response",dataIndex:"response",key:"response"},{title:"Excitement",dataIndex:"excitement",key:"excitement"}],Me=[{key:"1",name:"Vikhyat",response:"Yes",excitement:"5"},{key:"2",name:"Daniel",response:"Yes",excitement:"5"}],Je={marginRight:"1em"},Ye=(l.a.Title,n(338)),qe=l.a.Title,He=function(){return Object(h.jsxs)("div",{style:We,children:[Object(h.jsx)(qe,{level:3,children:"Event Name"}),Object(h.jsx)(qe,{level:4,children:"Event Info"}),Object(h.jsx)(qe,{level:4,children:"Invitees"}),Object(h.jsx)(Ge.a,{dataSource:Me,columns:Be}),Object(h.jsx)(b.a,{type:"primary",style:Je,children:"Yes"}),Object(h.jsx)(b.a,{type:"default",children:"No"}),Object(h.jsx)("br",{}),Object(h.jsx)(qe,{style:Ve,level:5,children:"How excited are you?"}),Object(h.jsx)(Ye.a,{style:Ke,min:1,max:5,defaultValue:3})]})},We={marginLeft:"5em",padding:"2em",border:"1px solid rgba(0,0,0,0.1)",borderRadius:"4px",marginBottom:"2em"},Ve={marginTop:"1.5em"},Ke={width:"300px"},Ze=function(){return Object(h.jsxs)("div",{children:[Object(h.jsx)(He,{}),Object(h.jsx)(b.a,{type:"primary",style:Je,children:"Submit"}),Object(h.jsx)(b.a,{type:"default",children:"Save Draft"})]})},Qe=l.a.Title,Xe={padding:"3em"},$e=function(){return Object(h.jsxs)("div",{style:Xe,children:[Object(h.jsx)(Qe,{children:"Group Date Name"}),Object(h.jsxs)(Qe,{level:3,children:["Status: ","Unfinalized"]}),Object(h.jsx)(Ze,{})]})},_e=n(341),et=n(209),tt=n(342),nt=n(343),ct={position:"fixed",height:"100vh",top:0,left:0,width:"50px",borderRight:"1px solid rgba(0,0,0,0.1)",zIndex:5,display:"flex",flexDirection:"column",alignItems:"center",justifyContent:"center"},at={listStyleType:"none",margin:0,padding:0},st=function(){var e=Object(c.useContext)(y).logout,t=Object(U.g)().pathname;return Object(h.jsx)("div",{className:"sidebar",style:ct,children:Object(h.jsxs)("ul",{style:at,children:[Object(h.jsx)("li",{className:"/"===t?"selected":"",children:Object(h.jsx)(i.b,{to:"/",children:Object(h.jsx)(K.a,{})})}),Object(h.jsx)("li",{className:"/propose"===t?"selected":"",children:Object(h.jsx)(i.b,{to:"/propose",children:Object(h.jsx)(_e.a,{})})}),Object(h.jsx)("li",{className:"/propose"===t?"selected":"",children:Object(h.jsx)(i.b,{to:"#",onClick:function(){return function(){var e=document.querySelector(".activity-feed");null===e||void 0===e||e.classList.remove("closed")}()},className:"activity-button",children:Object(h.jsx)(et.a,{})})}),Object(h.jsx)("li",{className:"/settings"===t?"selected":"",children:Object(h.jsx)(i.b,{to:"/settings",children:Object(h.jsx)(tt.a,{})})}),Object(h.jsx)("li",{children:Object(h.jsx)(i.b,{to:"#",onClick:function(){return e()},children:Object(h.jsx)(nt.a,{})})})]})})},rt=function(){return Object(h.jsxs)("div",{style:ot,children:[Object(h.jsx)("img",{src:"https://images.unsplash.com/photo-1637102838603-d1c02da68c66?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2070&q=80",style:lt}),Object(h.jsxs)("div",{children:[Object(h.jsx)("p",{style:dt,children:"Group Date Name"}),Object(h.jsx)("p",{children:"Group Date Status"})]})]})},it={height:"100vh",width:"400px",position:"fixed",top:0,left:0,backgroundColor:"rgba(247, 247, 247)",padding:"1em",zIndex:6},ot={display:"flex",alignItems:"center",padding:"1em",backgroundColor:"white",boxShadow:"0 1px 3px hsla(0, 0%, 0.2)"},lt={width:"160px",height:"100px",objectFit:"cover",borderRadius:"4px",marginRight:"1em"},dt={fontWeight:700},ut=function(){var e=Object(c.useState)(De),t=Object(o.a)(e,2),n=t[0],a=t[1],s=Object(c.useRef)(null);return Object(c.useEffect)((function(){var e;null===(e=s.current)||void 0===e||e.classList.add("closed");document.addEventListener("click",(function(e){var t,n,c,a=document.querySelector(".activity-button"),r=(null===(t=s.current)||void 0===t?void 0:t.contains(e.target))||(null===a||void 0===a?void 0:a.contains(e.target)),i=null===(n=s.current)||void 0===n?void 0:n.classList.contains("closed");r||i||(null===(c=s.current)||void 0===c||c.classList.add("closed"))}))}),[s]),Object(h.jsxs)("div",{className:"activity-feed",style:it,ref:s,children:[Object(h.jsx)(Le,{filterState:n,setFilterState:a}),Object(h.jsx)(rt,{})]})},jt=n.p+"static/media/concert3.c57aa10b.jpg",bt={width:"100%",height:"100%",objectFit:"cover"},ht={height:"fit-content",width:"fit-content",backgroundColor:"rgb(68, 141, 247)",position:"absolute",top:"25px",left:"35px"},mt={color:"white",fontSize:"55px",fontWeight:"bold",margin:"0"},pt={margin:"auto",height:"20vh",width:"100vw",display:"flex",justifyContent:"space-between",backgroundColor:"rgb(68, 141, 247)",color:"white",zIndex:6},xt=function(){return Object(h.jsxs)("header",{style:pt,children:[Object(h.jsxs)("div",{style:ht,children:[" ",Object(h.jsx)("h1",{style:mt,children:"Groupie"})," "]}),Object(h.jsx)("img",{src:jt,style:bt,alt:"logo"})]})};var ft=function(){var e=Object(c.useContext)(y),t=e.username,n=e.logout;return Object(c.useEffect)((function(){t&&function(e){var t;function n(){clearTimeout(t),t=setTimeout(e,6e4)}window.onload=n,document.onmousemove=n,document.onkeydown=n}(n)}),[t]),Object(h.jsxs)("div",{className:"App",children:[Object(h.jsxs)("div",{className:"content",children:[Object(h.jsx)(xt,{}),Object(h.jsxs)(U.c,{children:[!t&&Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(U.a,{exact:!0,path:"/",children:Object(h.jsx)(E,{})}),Object(h.jsx)(U.a,{path:"/signup",children:Object(h.jsx)(Se,{})})]}),t&&Object(h.jsxs)(h.Fragment,{children:[Object(h.jsx)(st,{}),Object(h.jsx)(ut,{}),Object(h.jsx)(U.a,{path:"/settings",children:Object(h.jsx)(Ce,{})}),Object(h.jsx)(U.a,{path:"/propose",children:Object(h.jsx)(re,{})}),Object(h.jsx)(U.a,{path:"/invite",children:Object(h.jsx)($e,{})}),Object(h.jsx)(U.a,{exact:!0,path:"/",children:Object(h.jsx)(Re,{})})]})]})]}),Object(h.jsx)(ze,{})]})},Ot=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,344)).then((function(t){var n=t.getCLS,c=t.getFID,a=t.getFCP,s=t.getLCP,r=t.getTTFB;n(e),c(e),a(e),s(e),r(e)}))},gt=function(e){var t=Object(U.f)(),n=Object(c.useState)({username:window.localStorage.getItem("username")}),a=Object(o.a)(n,2),s=a[0],r=a[1];return Object(h.jsx)(y.Provider,{value:{username:s.username,login:function(e){localStorage.setItem("username",e),r(Object(ie.a)(Object(ie.a)({},s),{},{username:e})),window.localStorage.setItem("failedAttempts",JSON.stringify(0))},logout:function(){null!==s.username&&(localStorage.removeItem("username"),r(Object(ie.a)(Object(ie.a)({},s),{},{username:null})),d.a.open({message:"You've been logged out",duration:2.5}),t.push("/"))}},children:e.children})};r.a.render(Object(h.jsx)(a.a.StrictMode,{children:Object(h.jsx)(i.a,{children:Object(h.jsx)(gt,{children:Object(h.jsx)(ft,{})})})}),document.getElementById("root")),Ot()}},[[329,1,2]]]);
//# sourceMappingURL=main.e8c2fedf.chunk.js.map