import { Moment } from 'moment';

export interface ISysAdmin {
    id?: number;
    username?: string;
    password?: string;
    avatar?: string;
    sex?: string;
    role?: number;
    phone?: string;
    createTime?: Moment;
    updateTime?: Moment;
}

export class SysAdmin implements ISysAdmin {
    constructor(
        public id?: number,
        public username?: string,
        public password?: string,
        public avatar?: string,
        public sex?: string,
        public role?: number,
        public phone?: string,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
