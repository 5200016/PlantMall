import { Moment } from 'moment';
import { ISysReceiverAddress } from 'app/shared/model//sys-receiver-address.model';

export interface ISysAppointment {
    id?: number;
    time?: Moment;
    remark?: any;
    status?: number;
    createTime?: Moment;
    updateTime?: Moment;
    receiverAddress?: ISysReceiverAddress;
}

export class SysAppointment implements ISysAppointment {
    constructor(
        public id?: number,
        public time?: Moment,
        public remark?: any,
        public status?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public receiverAddress?: ISysReceiverAddress
    ) {}
}
