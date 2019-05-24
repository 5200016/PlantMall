import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysAppointment } from 'app/shared/model//sys-appointment.model';
import { ISysOrder } from 'app/shared/model//sys-order.model';

export interface ISysReceiverAddress {
    id?: number;
    name?: string;
    phone?: string;
    area?: string;
    address?: any;
    status?: number;
    active?: boolean;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    appointments?: ISysAppointment[];
    orders?: ISysOrder[];
}

export class SysReceiverAddress implements ISysReceiverAddress {
    constructor(
        public id?: number,
        public name?: string,
        public phone?: string,
        public area?: string,
        public address?: any,
        public status?: number,
        public active?: boolean,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public appointments?: ISysAppointment[],
        public orders?: ISysOrder[]
    ) {
        this.active = this.active || false;
    }
}
