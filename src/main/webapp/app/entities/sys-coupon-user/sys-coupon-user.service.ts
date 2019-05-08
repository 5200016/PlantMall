import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysCouponUser } from 'app/shared/model/sys-coupon-user.model';

type EntityResponseType = HttpResponse<ISysCouponUser>;
type EntityArrayResponseType = HttpResponse<ISysCouponUser[]>;

@Injectable({ providedIn: 'root' })
export class SysCouponUserService {
    public resourceUrl = SERVER_API_URL + 'api/sys-coupon-users';

    constructor(protected http: HttpClient) {}

    create(sysCouponUser: ISysCouponUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCouponUser);
        return this.http
            .post<ISysCouponUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysCouponUser: ISysCouponUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCouponUser);
        return this.http
            .put<ISysCouponUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysCouponUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysCouponUser[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysCouponUser: ISysCouponUser): ISysCouponUser {
        const copy: ISysCouponUser = Object.assign({}, sysCouponUser, {
            createTime: sysCouponUser.createTime != null && sysCouponUser.createTime.isValid() ? sysCouponUser.createTime.toJSON() : null,
            updateTime: sysCouponUser.updateTime != null && sysCouponUser.updateTime.isValid() ? sysCouponUser.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysCouponUser: ISysCouponUser) => {
                sysCouponUser.createTime = sysCouponUser.createTime != null ? moment(sysCouponUser.createTime) : null;
                sysCouponUser.updateTime = sysCouponUser.updateTime != null ? moment(sysCouponUser.updateTime) : null;
            });
        }
        return res;
    }
}
