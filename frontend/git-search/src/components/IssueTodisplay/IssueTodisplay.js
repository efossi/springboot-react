import React, { Component } from 'react';
import Markdown from 'react-markdown'

export default class IssueTodisplay extends Component {

  render() {

    let title=this.props.details.title;
    let state= this.props.details.state;
    let body= this.props.details.body;
    let userLogin=this.props.details.user.login;
    let userAvatar=this.props.details.user.avatar_url;
    let assigneeLogin='';
    let assigneeAvatar='';
    if( typeof this.props.details.assignee === 'object' && this.props.details.assignee !== null){

      assigneeLogin=this.props.details.assignee.login;
      assigneeAvatar=this.props.details.assignee.avatar_url;
    }
    return (
      <div className="issue-to-display">
            <div className="issue-title">
              <p className="issue-main-title">{title}</p>
              <p>
              <span className="title-element elment-tag" >Status:</span>
              <span className="title-element" >{state}</span>
              <span className="title-element elment-tag">By: </span>
              <span className="title-element">{userLogin}  <img src={userAvatar} alt={userLogin}  height="30" width="30"/></span>
              
              {assigneeLogin &&
                <span>
                <span className="title-element elment-tag" > Assigned To:</span>
                <span className="title-element" >  {assigneeLogin} <img src={assigneeAvatar}  alt={assigneeLogin}  height="30" width="30"/></span> 
                </span>
              }
              </p>
            </div>
            <p className="issue-body">
            <Markdown source={body} />
            </p>
            
      </div>
    )
  }  
}
