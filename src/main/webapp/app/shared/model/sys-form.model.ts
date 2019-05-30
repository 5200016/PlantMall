import { ISysUser } from 'app/shared/model//sys-user.model';

export interface ISysForm {
    id?: number;
    formId?: string;
    validity?: number;
    user?: ISysUser;
}

export class SysForm implements ISysForm {
    constructor(public id?: number, public formId?: string, public validity?: number, public user?: ISysUser) {}
}
