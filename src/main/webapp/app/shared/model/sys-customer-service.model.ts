import { Moment } from 'moment';

export interface ISysCustomerService {
    id?: number;
    phone?: string;
    email?: string;
    createTime?: Moment;
    updateTime?: Moment;
}

export class SysCustomerService implements ISysCustomerService {
    constructor(public id?: number, public phone?: string, public email?: string, public createTime?: Moment, public updateTime?: Moment) {}
}
