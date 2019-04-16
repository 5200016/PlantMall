import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysCustomerService } from 'app/shared/model/sys-customer-service.model';
import { SysCustomerServiceService } from './sys-customer-service.service';
import { SysCustomerServiceComponent } from './sys-customer-service.component';
import { SysCustomerServiceDetailComponent } from './sys-customer-service-detail.component';
import { SysCustomerServiceUpdateComponent } from './sys-customer-service-update.component';
import { SysCustomerServiceDeletePopupComponent } from './sys-customer-service-delete-dialog.component';
import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';

@Injectable({ providedIn: 'root' })
export class SysCustomerServiceResolve implements Resolve<ISysCustomerService> {
    constructor(private service: SysCustomerServiceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysCustomerService> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysCustomerService>) => response.ok),
                map((sysCustomerService: HttpResponse<SysCustomerService>) => sysCustomerService.body)
            );
        }
        return of(new SysCustomerService());
    }
}

export const sysCustomerServiceRoute: Routes = [
    {
        path: 'sys-customer-service',
        component: SysCustomerServiceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCustomerService.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-customer-service/:id/view',
        component: SysCustomerServiceDetailComponent,
        resolve: {
            sysCustomerService: SysCustomerServiceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCustomerService.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-customer-service/new',
        component: SysCustomerServiceUpdateComponent,
        resolve: {
            sysCustomerService: SysCustomerServiceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCustomerService.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-customer-service/:id/edit',
        component: SysCustomerServiceUpdateComponent,
        resolve: {
            sysCustomerService: SysCustomerServiceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCustomerService.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysCustomerServicePopupRoute: Routes = [
    {
        path: 'sys-customer-service/:id/delete',
        component: SysCustomerServiceDeletePopupComponent,
        resolve: {
            sysCustomerService: SysCustomerServiceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCustomerService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
