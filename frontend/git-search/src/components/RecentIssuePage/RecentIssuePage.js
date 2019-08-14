import React, { Component, Fragment } from 'react';
import { withRouter } from 'react-router-dom';
import Utils from '../../utils/utils';
import queryString  from 'query-string';
import { API_BASE } from '../../utils/constants';
import IssueTodisplay from '../IssueTodisplay/IssueTodisplay.js';
import Pagination from '../Pagination/Pagination.js';
import moment from 'moment/min/moment-with-locales';

class RecentIssuePage extends Component {
  constructor(props) {
    super(props);
    let parsed = queryString
            .parse(this.props.location ? this.props.location.search :'');
    let nbOfDays=7;
    if(parsed.days && parseInt(10,parsed.days)>0){
      nbOfDays=parsed.days;
    }

    const startDate=moment().add(-nbOfDays, 'day').toISOString() ;
    // const firstPage=`${API_BASE['GIT_API_BASE_URL']}/repos/angular/angular/issues?since=${startDate}`
    console.log("nbOfDays:"+nbOfDays+" startDate:"+startDate);

    let firstPage=`${API_BASE['GIT_API_BASE_URL']}/repos/angular/angular/issues?since=${startDate}`;

    this.props.appActions.updateFirstPageUrl(firstPage);

    let _this=this;
    let fetchData = Utils.fetchData.bind(Utils);
    fetchData(
      'GET',
      `${firstPage}`,
      false
    )
    .then(
      function(fetchResponse){
        _this.props.appActions.handleIssues(fetchResponse);
      }
    );
  }
  
  render() {

    let listComps_issuesToDisplayList={};
    return (
      <Fragment >
        <section className="contain--slim">
          <div className="page-header">
            <h1>Git Search</h1>
          </div>

            <div className="section-heading issue_section">
                {this.props.issuesToDisplay.map((row, index) => {

                  let itemComp = (row._componentId) ? listComps_issuesToDisplayList[row._componentId] : 
                    <IssueTodisplay   dataSheetId={'listData1'}   appActions={this.props.appActions} 
                    {...row} {...this.props} />;
                  return (
                    <div key={row.details.number}>
                      {itemComp}
                    </div>
                  )
                })}          
            </div>
              {this.props.currentTotalNumberOfPages > 1 &&
                <Pagination {...this.props} />
              }
        </section>
      </Fragment>
    );
  }
}
export default withRouter(RecentIssuePage);
