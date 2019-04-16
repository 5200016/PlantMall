import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysOrder } from 'app/shared/model/sys-order.model';

type EntityResponseType = HttpResponse<ISysOrder>;
type EntityArrayResponseType = HttpResponse<ISysOrder[]>;

@Injectable({ providedIn: 'root' })
export class SysOrderService {
    public resourceUrl = SERVER_API_URL + 'api/sys-orders';

    constructor(protected http: HttpClient) {}

    create(sysOrder: ISysOrder): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysOrder);
        return this.http
            .post<ISysOrder>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysOrder: ISysOrder): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysOrder);
        return this.http
            .put<ISysOrder>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysOrder: ISysOrder): ISysOrder {
        const copy: ISysOrder = Object.assign({}, sysOrder, {
            createTime: sysOrder.createTime != null && sysOrder.createTime.isValid() ? sysOrder.createTime.toJSON() : null,
            updateTime: sysOrder.updateTime != null && sysOrder.updateTime.isValid() ? sysOrder.updateTime.toJSON() : null
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
            res.body.forEach((sysOrder: ISysOrder) => {
                sysOrder.createTime = sysOrder.createTime != null ? moment(sysOrder.createTime) : null;
                sysOrder.updateTime = sysOrder.updateTime != null ? moment(sysOrder.updateTime) : null;
            });
        }
        return res;
    }
}
