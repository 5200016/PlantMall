/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysCustomerServiceDeleteDialogComponent } from 'app/entities/sys-customer-service/sys-customer-service-delete-dialog.component';
import { SysCustomerServiceService } from 'app/entities/sys-customer-service/sys-customer-service.service';

describe('Component Tests', () => {
    describe('SysCustomerService Management Delete Component', () => {
        let comp: SysCustomerServiceDeleteDialogComponent;
        let fixture: ComponentFixture<SysCustomerServiceDeleteDialogComponent>;
        let service: SysCustomerServiceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCustomerServiceDeleteDialogComponent]
            })
                .overrideTemplate(SysCustomerServiceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCustomerServiceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCustomerServiceService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
