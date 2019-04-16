import { Moment } from 'moment';

export interface ISysBanner {
    id?: number;
    image?: string;
    link?: string;
    sort?: number;
    createTime?: Moment;
    updateTime?: Moment;
}

export class SysBanner implements ISysBanner {
    constructor(
        public id?: number,
        public image?: string,
        public link?: string,
        public sort?: number,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
