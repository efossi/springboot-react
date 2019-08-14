import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route,
} from 'react-router-dom';
import RecentIssuePage from './RecentIssuePage/RecentIssuePage.js';
import { API_BASE } from '../utils/constants';
import Utils from '../utils/utils';
import moment from 'moment/min/moment-with-locales';


export default class Main extends Component {

  constructor(props) {
    super(props);

    let nbOfDays=7;

    const startDate=moment().add(-nbOfDays, 'day').toISOString() ;
    const firstPage=`${API_BASE['GIT_API_BASE_URL']}/repos/angular/angular/issues?since=${startDate}`;

    this.state = {
      currentPage:1,
      previousPage:1,
      lastPage:1,
      nextPage:1,
      firstPageUrl:firstPage,
      currentTotalNumberOfPages:1,
      days:nbOfDays,
      issuesToDisplay:[
      ],

    }
  }
  updateFirstPageUrl=(firstPage)=>{
    console.log("updateFirstPageUrl");
    console.log(firstPage);
    this.setState((state, props) => ({
      ['firstPageUrl']:firstPage
    }));
    console.log(this.state);

  }
  loadPage=(param)=>{

    console.log("param.target.value");
    console.log(param.target.value);
    const currentPage=param.target.value;
    this.setState((state, props) => ({
      ['currentPage']:currentPage
    }));

    let _this=this;
    let fetchData = Utils.fetchData.bind(Utils);
    fetchData(
      'GET',
      `${_this.state.firstPageUrl}&page=${currentPage}`,
      false
    )
    .then(
      function(fetchResponse){
        _this.handleIssues(fetchResponse);
      }
    );

    console.log(this.state);
  }
  handleIssues=(fetchResponse)=>{
    const toDisplay=Object.entries(fetchResponse.data)
                      .map(item=>(
                        {details:item[1]}
                      ));

    this.setState((state, props) => ({
      ['issuesToDisplay']:toDisplay
    }));

    try{
      if(fetchResponse.headers && fetchResponse.headers.link){
        let linkHeader=fetchResponse.headers.link;
        console.log("linkHeader"+linkHeader);
        let nbOfPages=this.getPageFromLinkHeader(linkHeader,'last');
        if(nbOfPages){
          this.setState((state, props) => ({
            //['currentTotalNumberOfPages']:parseInt(10,linkHeader.split(',')[1].split('page=')[1].replace(/\>.*/,''))
            ['currentTotalNumberOfPages']:nbOfPages
          }));
        }
      }
    }catch(error){
      console.log("Error extracting currentTotalNumberOfPages");
    }

  }  

  getPageFromLinkHeader=(linkHeader,pageType)=>{
    let arr=linkHeader.split(',').filter(page => page.search('rel="'+pageType+'"') >-1 );
    if(arr && arr.length){
      //return arr[0];
      return parseInt(10,arr[0].split('page=')[1].replace(/\>.*/,''));
    }else{
      return 0;
    }

  }


  render() {
    let screenProps = {
        ...this.state,
        appActions: this,
      };
    return <AppRoutes   {...screenProps} {...this.state}/>
  }


}
  const basename = process.env.NODE_ENV === 'production' ? '/' : '/';

  const AppRoutes = ( props) => {
    return <Routes {...props}  />
  };

  const Routes = ({...props}) => {
    return (
    <Router basename={basename}>

      <React.Fragment>
        <RouteWithErrorBar exact path="/" component={RecentIssuePage} {...props}/>

      </React.Fragment>
    </Router>)
  };

  const RouteWithErrorBar = ({component: Component, ...props}) => {
    return (
      <Route
        {...props}
        render={(propsVar) => <Component {...props}   />}
      />);
  } 




 

  