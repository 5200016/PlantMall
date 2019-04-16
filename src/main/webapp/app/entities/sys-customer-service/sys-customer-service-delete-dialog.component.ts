import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';
import { SysCustomerServiceService } from './sys-customer-service.service';

@Component({
    selector: 'jhi-sys-customer-service-delete-dialog',
    templateUrl: './sys-customer-service-delete-dialog.component.html'
})
export class SysCustomerServiceDeleteDialogComponent {
    sysCustomerService: ISysCustomerService;

    constructor(
        protected sysCustomerServiceService: SysCustomerServiceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysCustomerServiceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysCustomerServiceListModification',
                content: 'Deleted an sysCustomerService'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-customer-service-delete-popup',
    template: ''
})
export class SysCustomerServiceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCustomerService }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysCustomerServiceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysCustomerService = sysCustomerService;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
