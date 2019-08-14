import React from 'react';
import { shallow } from 'enzyme';

import RecentIssuePage from './RecentIssuePage';

describe('RecentIssuePage tests', () => {

  it('renders contain--slim, page-header', () => {
    const items = {};
    const wrapper = shallow(<RecentIssuePage {...items} />);

    expect(wrapper.find('.contain--slim')).toBeDefined();
    expect(wrapper.find('.page-header')).toBeDefined();
  });


});