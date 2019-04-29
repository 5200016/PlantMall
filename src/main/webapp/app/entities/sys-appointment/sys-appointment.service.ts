import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysAppointment } from 'app/shared/model/sys-appointment.model';

type EntityResponseType = HttpResponse<ISysAppointment>;
type EntityArrayResponseType = HttpResponse<ISysAppointment[]>;

@Injectable({ providedIn: 'root' })
export class SysAppointmentService {
    public resourceUrl = SERVER_API_URL + 'api/sys-appointments';

    constructor(protected http: HttpClient) {}

    create(sysAppointment: ISysAppointment): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysAppointment);
        return this.http
            .post<ISysAppointment>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysAppointment: ISysAppointment): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysAppointment);
        return this.http
            .put<ISysAppointment>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysAppointment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysAppointment[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysAppointment: ISysAppointment): ISysAppointment {
        const copy: ISysAppointment = Object.assign({}, sysAppointment, {
            time: sysAppointment.time != null && sysAppointment.time.isValid() ? sysAppointment.time.toJSON() : null,
            createTime:
                sysAppointment.createTime != null && sysAppointment.createTime.isValid() ? sysAppointment.createTime.toJSON() : null,
            updateTime: sysAppointment.updateTime != null && sysAppointment.updateTime.isValid() ? sysAppointment.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.time = res.body.time != null ? moment(res.body.time) : null;
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysAppointment: ISysAppointment) => {
                sysAppointment.time = sysAppointment.time != null ? moment(sysAppointment.time) : null;
                sysAppointment.createTime = sysAppointment.createTime != null ? moment(sysAppointment.createTime) : null;
                sysAppointment.updateTime = sysAppointment.updateTime != null ? moment(sysAppointment.updateTime) : null;
            });
        }
        return res;
    }
}
