/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderProductDeleteDialogComponent } from 'app/entities/sys-order-product/sys-order-product-delete-dialog.component';
import { SysOrderProductService } from 'app/entities/sys-order-product/sys-order-product.service';

describe('Component Tests', () => {
    describe('SysOrderProduct Management Delete Component', () => {
        let comp: SysOrderProductDeleteDialogComponent;
        let fixture: ComponentFixture<SysOrderProductDeleteDialogComponent>;
        let service: SysOrderProductService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderProductDeleteDialogComponent]
            })
                .overrideTemplate(SysOrderProductDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysOrderProductDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysOrderProductService);
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
