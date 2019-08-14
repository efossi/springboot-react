import React, { Component } from 'react';

export default class Pagination extends Component {
  render() {
    return (
      <div className="contain pagination">
          {[...Array(this.props.currentTotalNumberOfPages).keys()].map((row, index) => {
            let itemClasses =  ` btn ${this.props.currentPage==index+1 ? 'active' : ''}`  ;

            return (

              <span key={index}>

                { ( index+1==1 || 
                    this.props.currentPage==index+1 || 
                    this.props.currentPage==index   || 
                    this.props.currentPage==index+2 || 
                    this.props.currentPage==index-1 || 
                    this.props.currentPage==index+3 || 
                    this.props.currentPage==index-2 || 
                    this.props.currentPage==index+4 || 
                    this.props.currentTotalNumberOfPages==index+1 
                    ) ? 
                  <button key={row} 
                  onClick={this.props.appActions.loadPage}
                  value={index+1}
                  className={itemClasses}>
                    {index+1}
                  </button>
                : ''
                }
              </span>

            )
          })}
      </div>
    )
  }
}
